package adstest;

import adstest.entities.BidRequest;
import adstest.utils.Utils;

import java.util.Queue;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author kkulagin
 * @since 31.01.2016
 */
public class AsyncPrinter {

  private final Serializer serializer;
  private final boolean[] stop = new boolean[]{false};
  private final Queue<MatchResult> printQueue = new ConcurrentLinkedQueue<>();
  private final ExecutorService service;
  private Future future;

  public AsyncPrinter(Serializer serializer) {
    this.serializer = serializer;
//    service = Executors.newSingleThreadExecutor();

    service = Executors.newWorkStealingPool();
  }

  public void start() {
    future = service.submit((Callable) () -> {
      while (!stop[0]) {
        MatchResult poll = null;
        while ((poll = printQueue.poll()) != null) {
          serializer.printRequestResult(poll.bidRequest, poll.campaignIds);
        }
      }
      return true;
    });
  }

  public void print(BidRequest bidRequest, Set<Long> campaignIds) {
    printQueue.add(new MatchResult(bidRequest, campaignIds));
  }

  public void stop() {
    stop[0] = true;
    try {
      future.get();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException(e);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    shutDown();
  }

  private void shutDown() {
    try {
      service.shutdown();
      try {
        service.awaitTermination(1, TimeUnit.MINUTES);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private class MatchResult {
    private final BidRequest bidRequest;
    private final long[] campaignIds;

    public MatchResult(BidRequest bidRequest, Set<Long> campaignIds) {
      this.bidRequest = bidRequest;
      this.campaignIds = Utils.toArray(campaignIds);
    }
  }


}
