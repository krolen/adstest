package adstest.entities;

/**
 * Eventually this enum should contain all possible countries.For a sample purpose we define a few.
 * I think there are about 260 country codes avail.
 */
public enum Country {

  AD(0),
  AE(1),
  AF(2),
  AG(3),
  AI(4),
  AL(5),
  AM(6),
  AN(7),
  AO(8),
  AQ(9),
  AR(10),
  AS(11),
  AT(12),
  AU(13),
  AW(14),
  AX(15),
  AZ(16),
  BA(17),
  BB(18),
  BD(19),
  BE(20),
  BF(21),
  BG(22),
  BH(23),
  BI(24),
  BJ(25),
  BL(26),
  BM(27),
  BN(28),
  BO(29),
  BQ(30),
  BR(31),
  BS(32),
  BT(33),
  BV(34),
  BW(35),
  BY(36),
  BZ(37),
  CA(38),
  CC(39),
  CD(40),
  CF(41),
  CG(42),
  CH(43),
  CI(44),
  CK(45),
  CL(46),
  CM(47),
  CN(48),
  CO(49),
  CR(50),
  CU(51),
  CV(52),
  CW(53),
  CX(54),
  CY(55),
  CZ(56),
  DE(57),
  DJ(58),
  DK(59),
  DM(60),
  DO(61),
  DZ(62),
  EC(63),
  EE(64),
  EG(65),
  EH(66),
  ER(67),
  ES(68),
  ET(69),
  FI(70),
  FJ(71),
  FK(72),
  FM(73),
  FO(74),
  FR(75),
  GA(76),
  GB(77),
  GD(78),
  GE(79),
  GF(80),
  GG(81),
  GH(82),
  GI(83),
  GL(84),
  GM(85),
  GN(86),
  GP(87),
  GQ(88),
  GR(89),
  GS(90),
  GT(91),
  GU(92),
  GW(93),
  GY(94),
  HK(95),
  HM(96),
  HN(97),
  HR(98),
  HT(99),
  ZW(100),
  HU(101),
  ID(102),
  IE(103),
  IL(104),
  IM(105),
  IN(106),
  IO(107),
  IQ(108),
  IR(109),
  IS(110),
  IT(111),
  JE(112),
  JM(113),
  JO(114),
  JP(115),
  KE(116),
  KG(117),
  KH(118),
  KI(119),
  KM(120),
  KN(121),
  KP(122),
  KR(123),
  KW(124),
  KY(125),
  KZ(126),
  LA(127),
  LB(128),
  LC(129),
  LI(130),
  LK(131),
  LR(132),
  LS(133),
  LT(134),
  LU(135),
  LV(136),
  LY(137),
  MA(138),
  MC(139),
  MD(140),
  ME(141),
  MF(142),
  MG(143),
  MH(144),
  MK(145),
  ML(146),
  MM(147),
  MN(148),
  MO(149),
  MP(150),
  MQ(151),
  MR(152),
  MS(153),
  MT(154),
  MU(155),
  MV(156),
  MW(157),
  MX(158),
  MY(159),
  MZ(160),
  NA(161),
  NC(162),
  NE(163),
  NF(164),
  NG(165),
  NI(166),
  NL(167),
  NO(168),
  NP(169),
  NR(170),
  NU(171),
  NZ(172),
  OM(173),
  PA(174),
  PE(175),
  PF(176),
  PG(177),
  PH(178),
  PK(179),
  PL(180),
  PM(181),
  PN(182),
  PR(183),
  PS(184),
  PT(185),
  PW(186),
  PY(187),
  QA(188),
  RE(189),
  RO(190),
  RS(191),
  RU(192),
  RW(193),
  SA(194),
  SB(195),
  SC(196),
  SD(197),
  SE(198),
  SG(199),
  SH(200),
  SI(201),
  SJ(202),
  SK(203),
  SL(204),
  SM(205),
  SN(206),
  SO(207),
  SR(208),
  SS(209),
  ST(210),
  SV(211),
  SX(212),
  SY(213),
  SZ(214),
  TC(215),
  TD(216),
  TF(217),
  TG(218),
  TH(219),
  TJ(220),
  TK(221),
  TL(222),
  TM(223),
  TN(224),
  TO(225),
  TR(226),
  TT(227),
  TV(228),
  TW(229),
  TZ(230),
  UA(231),
  UG(232),
  UM(233),
  US(234),
  UY(235),
  UZ(236),
  VA(237),
  VC(238),
  VE(239),
  VG(240),
  VI(241),
  VN(242),
  VU(243),
  WF(244),
  WS(245),
  YE(246),
  YT(247),
  ZA(248),
  ZM(249);

  private int value;

  Country(int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }

  public static int count() {
    return Country.values().length;
  }

  public static Country fromValue(int value) {
    for (Country country : Country.values()) {
      if(country.value() == value) {
        return country;
      }
    }
    throw new IllegalArgumentException("code not found " + value);
  }

}