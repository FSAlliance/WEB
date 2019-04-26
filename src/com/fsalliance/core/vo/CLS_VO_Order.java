package com.fsalliance.core.vo;

import java.sql.Timestamp;

/**
 * 订单
 * @author yuanxueyuan
 */
public class CLS_VO_Order {
	private String id;//订单编号
	private Timestamp createTime;//创建时间
	private String auctionTitle;//商品信息
	private String auctionId;//商品ID
	private String exnickname;//所属店铺名称
	private int auctionNum;//商品数
	private double payPrice;//商品单价
	private int payStatus;//订单状态(订单付款：0， 订单失效：1 )12表示订单付款、13表示订单失效、3表示订单付款
	private int tkbiztag;//订单类型（1：淘宝 2：天猫）
	private double discountAndSubsidyToString;//收入比率
	private double shareRate;//分成比率
	private double totalAlipayFeeString;//付款金额
	private double feeString;//效果预估
	private double settlementAmount;//结算金额
	private double forecastIncome;//预估收入
	private Timestamp earningTime;//结算时间
	private double finalDiscountTostring;//佣金比率
	private double commissionAmount;//佣金金额
	private double subsidyRatio;//补贴比率
	private double subsidyAmount;//补贴金额
	private int subsidyType;//补贴类型
	private int terminalType;//成交平台
	private String thirdService;//第三方服务来源
	private String category;//类目名称
	private String sourceMedium;//来源媒体
	private String sourceMediumName;//来源媒体名称
	private String adsenseId;//广告位ID
	private String adsenseName;//广告位名称
	public String getId() {
		return id;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public String getAuctionTitle() {
		return auctionTitle;
	}
	public String getAuctionId() {
		return auctionId;
	}
	public String getExnickname() {
		return exnickname;
	}
	public int getAuctionNum() {
		return auctionNum;
	}
	public double getPayPrice() {
		return payPrice;
	}
	public int getPayStatus() {
		return payStatus;
	}
	public int getTkbiztag() {
		return tkbiztag;
	}
	public double getDiscountAndSubsidyToString() {
		return discountAndSubsidyToString;
	}
	public double getShareRate() {
		return shareRate;
	}
	public double getTotalAlipayFeeString() {
		return totalAlipayFeeString;
	}
	public double getFeeString() {
		return feeString;
	}
	public double getSettlementAmount() {
		return settlementAmount;
	}
	public double getForecastIncome() {
		return forecastIncome;
	}
	public Timestamp getEarningTime() {
		return earningTime;
	}
	public double getFinalDiscountTostring() {
		return finalDiscountTostring;
	}
	public double getCommissionAmount() {
		return commissionAmount;
	}
	public double getSubsidyRatio() {
		return subsidyRatio;
	}
	public double getSubsidyAmount() {
		return subsidyAmount;
	}
	public int getSubsidyType() {
		return subsidyType;
	}
	public int getTerminalType() {
		return terminalType;
	}
	public String getThirdService() {
		return thirdService;
	}
	public String getCategory() {
		return category;
	}
	public String getSourceMedium() {
		return sourceMedium;
	}
	public String getSourceMediumName() {
		return sourceMediumName;
	}
	public String getAdsenseId() {
		return adsenseId;
	}
	public String getAdsenseName() {
		return adsenseName;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public void setAuctionTitle(String auctionTitle) {
		this.auctionTitle = auctionTitle;
	}
	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}
	public void setExnickname(String exnickname) {
		this.exnickname = exnickname;
	}
	public void setAuctionNum(int auctionNum) {
		this.auctionNum = auctionNum;
	}
	public void setPayPrice(double payPrice) {
		this.payPrice = payPrice;
	}
	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}
	public void setTkbiztag(int tkbiztag) {
		this.tkbiztag = tkbiztag;
	}
	public void setDiscountAndSubsidyToString(double discountAndSubsidyToString) {
		this.discountAndSubsidyToString = discountAndSubsidyToString;
	}
	public void setShareRate(double shareRate) {
		this.shareRate = shareRate;
	}
	public void setTotalAlipayFeeString(double totalAlipayFeeString) {
		this.totalAlipayFeeString = totalAlipayFeeString;
	}
	public void setFeeString(double feeString) {
		this.feeString = feeString;
	}
	public void setSettlementAmount(double settlementAmount) {
		this.settlementAmount = settlementAmount;
	}
	public void setForecastIncome(double forecastIncome) {
		this.forecastIncome = forecastIncome;
	}
	public void setEarningTime(Timestamp earningTime) {
		this.earningTime = earningTime;
	}
	public void setFinalDiscountTostring(double finalDiscountTostring) {
		this.finalDiscountTostring = finalDiscountTostring;
	}
	public void setCommissionAmount(double commissionAmount) {
		this.commissionAmount = commissionAmount;
	}
	public void setSubsidyRatio(double subsidyRatio) {
		this.subsidyRatio = subsidyRatio;
	}
	public void setSubsidyAmount(double subsidyAmount) {
		this.subsidyAmount = subsidyAmount;
	}
	public void setSubsidyType(int subsidyType) {
		this.subsidyType = subsidyType;
	}
	public void setTerminalType(int terminalType) {
		this.terminalType = terminalType;
	}
	public void setThirdService(String thirdService) {
		this.thirdService = thirdService;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setSourceMedium(String sourceMedium) {
		this.sourceMedium = sourceMedium;
	}
	public void setSourceMediumName(String sourceMediumName) {
		this.sourceMediumName = sourceMediumName;
	}
	public void setAdsenseId(String adsenseId) {
		this.adsenseId = adsenseId;
	}
	public void setAdsenseName(String adsenseName) {
		this.adsenseName = adsenseName;
	}
	@Override
	public String toString() {
		return "CLS_VO_Order [adsenseId=" + adsenseId + ", adsenseName="
				+ adsenseName + ", auctionId=" + auctionId + ", auctionNum="
				+ auctionNum + ", auctionTitle=" + auctionTitle + ", category="
				+ category + ", commissionAmount=" + commissionAmount
				+ ", createTime=" + createTime
				+ ", discountAndSubsidyToString=" + discountAndSubsidyToString
				+ ", earningTime=" + earningTime + ", exnickname=" + exnickname
				+ ", feeString=" + feeString + ", finalDiscountTostring="
				+ finalDiscountTostring + ", forecastIncome=" + forecastIncome
				+ ", id=" + id + ", payPrice=" + payPrice + ", payStatus="
				+ payStatus + ", settlementAmount=" + settlementAmount
				+ ", shareRate=" + shareRate + ", sourceMedium=" + sourceMedium
				+ ", sourceMediumName=" + sourceMediumName + ", subsidyAmount="
				+ subsidyAmount + ", subsidyRatio=" + subsidyRatio
				+ ", subsidyType=" + subsidyType + ", terminalType="
				+ terminalType + ", thirdService=" + thirdService
				+ ", tkbiztag=" + tkbiztag + ", totalAlipayFeeString="
				+ totalAlipayFeeString + "]";
	}
	
}
