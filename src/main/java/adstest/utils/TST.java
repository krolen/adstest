package adstest.utils;

import adstest.entities.BidRequest;
import adstest.entities.Campaign;

import java.util.HashSet;
import java.util.Set;

/**
 * Class shamelessly taken from http://algs4.cs.princeton.edu/52trie/TST.java.html
 * and modified for our purposes
 */
public class TST {
  private Node root;   // root of TST

  private static class Node {
    private char c;                        // character
    private Node left, mid, right;  // left, middle, and right subtries
    private TSTNodeValue val;                     // value associated with string
  }

  /**
   * Initializes an empty string symbol table.
   */
  public TST() {
  }

  /**
   * Returns the value associated with the given key.
   *
   * @param key the key
   * @return the value associated with the given key if the key is in the symbol table
   * and <tt>null</tt> if the key is not in the symbol table
   * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
   */
  public Set<Long> get(BidRequest bidRequest) {
    char[] requestDomain = Utils.extractAndReverseDomainWithDot(bidRequest.getPageUrl());
    Set<Long> result = new HashSet<>();
    getMatchingCampaigns(root, requestDomain, 0, result, bidRequest);
    return result;
  }

  private Node getMatchingCampaigns(Node x, char[] key, int d, Set<Long> result, BidRequest bidRequest) {
    if (x == null) return null;
    char c = key[d];
    if (c < x.c) {
      return getMatchingCampaigns(x.left, key, d, result, bidRequest);
    } else if (c > x.c) {
      return getMatchingCampaigns(x.right, key, d, result, bidRequest);
    } else {
      if(c == '.' && x.val != null) {
        result.addAll(x.val.matchedCampaignIds(bidRequest.getCountryValue(), bidRequest.getDimensionsAsInts()));
      }
      if (d < key.length - 1) {
        return getMatchingCampaigns(x.mid, key, d + 1, result, bidRequest);
      } else {
        return x;
      }
    }
  }

  public void put(Campaign val) {
    root = put(root, Utils.appendDotAndReverse(val.getDomain().toCharArray()), val, 0);
  }

  private Node put(Node x, char[] key, Campaign val, int d) {
    char c = key[d];
    if (x == null) {
      x = new Node();
      x.c = c;
    }
    if (c < x.c) {
      x.left = put(x.left, key, val, d);
    } else if (c > x.c) {
      x.right = put(x.right, key, val, d);
    } else if (d < key.length - 1) {
      x.mid = put(x.mid, key, val, d + 1);
    } else {
      if (x.val == null) {
        x.val = new TSTNodeValue();
      }
      x.val.addCampaign(val);
    }
    return x;
  }


}