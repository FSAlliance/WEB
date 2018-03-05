package com.fsalliance.core.po;

import java.sql.Timestamp;

/**
 * TabOrder entity. @author MyEclipse Persistence Tools
 */

public class TabOrder implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields

	private String SId;

	private Timestamp dtCreateTime;

	private String SAuctionTitle;

	private String SAuctionId;

	private String SExnickname;

	private Integer IAuctionNum;

	private Double DPayPrice;

	private Integer IPayStatus;

	private Integer ITkbiztag;

	private Double DDiscountAndSubsidyToString;

	private Double DShareRate;

	private Double DTotalAlipayFeeString;

	private Double DFeeString;

	private Double DSettlementAmount;

	private Double DForecastIncome;

	private Timestamp dtEarningTime;

	private Double DFinalDiscountTostring;

	private Double DCommissionAmount;

	private Double DSubsidyRatio;

	private Double DSubsidyAmount;

	private Integer ISubsidyType;

	private Integer ITerminalType;

	private String SThirdService;

	private String SCategory;

	private String SSourceMedium;

	private String SSourceMediumName;

	private String SAdsenseId;

	private String SAdsenseName;

	// Constructors

	/** default constructor */
	public TabOrder() {
	}

	/** minimal constructor */
	public TabOrder(String SId) {
		this.SId = SId;
	}

	/** full constructor */
	public TabOrder(String SId, Timestamp dtCreateTime, String SAuctionTitle,
			String SAuctionId, String SExnickname, Integer IAuctionNum,
			Double DPayPrice, Integer IPayStatus, Integer ITkbiztag,
			Double DDiscountAndSubsidyToString, Double DShareRate,
			Double DTotalAlipayFeeString, Double DFeeString,
			Double DSettlementAmount, Double DForecastIncome,
			Timestamp dtEarningTime, Double DFinalDiscountTostring,
			Double DCommissionAmount, Double DSubsidyRatio,
			Double DSubsidyAmount, Integer ISubsidyType, Integer ITerminalType,
			String SThirdService, String SCategory, String SSourceMedium,
			String SSourceMediumName, String SAdsenseId, String SAdsenseName) {
		this.SId = SId;
		this.dtCreateTime = dtCreateTime;
		this.SAuctionTitle = SAuctionTitle;
		this.SAuctionId = SAuctionId;
		this.SExnickname = SExnickname;
		this.IAuctionNum = IAuctionNum;
		this.DPayPrice = DPayPrice;
		this.IPayStatus = IPayStatus;
		this.ITkbiztag = ITkbiztag;
		this.DDiscountAndSubsidyToString = DDiscountAndSubsidyToString;
		this.DShareRate = DShareRate;
		this.DTotalAlipayFeeString = DTotalAlipayFeeString;
		this.DFeeString = DFeeString;
		this.DSettlementAmount = DSettlementAmount;
		this.DForecastIncome = DForecastIncome;
		this.dtEarningTime = dtEarningTime;
		this.DFinalDiscountTostring = DFinalDiscountTostring;
		this.DCommissionAmount = DCommissionAmount;
		this.DSubsidyRatio = DSubsidyRatio;
		this.DSubsidyAmount = DSubsidyAmount;
		this.ISubsidyType = ISubsidyType;
		this.ITerminalType = ITerminalType;
		this.SThirdService = SThirdService;
		this.SCategory = SCategory;
		this.SSourceMedium = SSourceMedium;
		this.SSourceMediumName = SSourceMediumName;
		this.SAdsenseId = SAdsenseId;
		this.SAdsenseName = SAdsenseName;
	}

	// Property accessors

	public String getSId() {
		return this.SId;
	}

	public void setSId(String SId) {
		this.SId = SId;
	}

	public Timestamp getDtCreateTime() {
		return this.dtCreateTime;
	}

	public void setDtCreateTime(Timestamp dtCreateTime) {
		this.dtCreateTime = dtCreateTime;
	}

	public String getSAuctionTitle() {
		return this.SAuctionTitle;
	}

	public void setSAuctionTitle(String SAuctionTitle) {
		this.SAuctionTitle = SAuctionTitle;
	}

	public String getSAuctionId() {
		return this.SAuctionId;
	}

	public void setSAuctionId(String SAuctionId) {
		this.SAuctionId = SAuctionId;
	}

	public String getSExnickname() {
		return this.SExnickname;
	}

	public void setSExnickname(String SExnickname) {
		this.SExnickname = SExnickname;
	}

	public Integer getIAuctionNum() {
		return this.IAuctionNum;
	}

	public void setIAuctionNum(Integer IAuctionNum) {
		this.IAuctionNum = IAuctionNum;
	}

	public Double getDPayPrice() {
		return this.DPayPrice;
	}

	public void setDPayPrice(Double DPayPrice) {
		this.DPayPrice = DPayPrice;
	}

	public Integer getIPayStatus() {
		return this.IPayStatus;
	}

	public void setIPayStatus(Integer IPayStatus) {
		this.IPayStatus = IPayStatus;
	}

	public Integer getITkbiztag() {
		return this.ITkbiztag;
	}

	public void setITkbiztag(Integer ITkbiztag) {
		this.ITkbiztag = ITkbiztag;
	}

	public Double getDDiscountAndSubsidyToString() {
		return this.DDiscountAndSubsidyToString;
	}

	public void setDDiscountAndSubsidyToString(
			Double DDiscountAndSubsidyToString) {
		this.DDiscountAndSubsidyToString = DDiscountAndSubsidyToString;
	}

	public Double getDShareRate() {
		return this.DShareRate;
	}

	public void setDShareRate(Double DShareRate) {
		this.DShareRate = DShareRate;
	}

	public Double getDTotalAlipayFeeString() {
		return this.DTotalAlipayFeeString;
	}

	public void setDTotalAlipayFeeString(Double DTotalAlipayFeeString) {
		this.DTotalAlipayFeeString = DTotalAlipayFeeString;
	}

	public Double getDFeeString() {
		return this.DFeeString;
	}

	public void setDFeeString(Double DFeeString) {
		this.DFeeString = DFeeString;
	}

	public Double getDSettlementAmount() {
		return this.DSettlementAmount;
	}

	public void setDSettlementAmount(Double DSettlementAmount) {
		this.DSettlementAmount = DSettlementAmount;
	}

	public Double getDForecastIncome() {
		return this.DForecastIncome;
	}

	public void setDForecastIncome(Double DForecastIncome) {
		this.DForecastIncome = DForecastIncome;
	}

	public Timestamp getDtEarningTime() {
		return this.dtEarningTime;
	}

	public void setDtEarningTime(Timestamp dtEarningTime) {
		this.dtEarningTime = dtEarningTime;
	}

	public Double getDFinalDiscountTostring() {
		return this.DFinalDiscountTostring;
	}

	public void setDFinalDiscountTostring(Double DFinalDiscountTostring) {
		this.DFinalDiscountTostring = DFinalDiscountTostring;
	}

	public Double getDCommissionAmount() {
		return this.DCommissionAmount;
	}

	public void setDCommissionAmount(Double DCommissionAmount) {
		this.DCommissionAmount = DCommissionAmount;
	}

	public Double getDSubsidyRatio() {
		return this.DSubsidyRatio;
	}

	public void setDSubsidyRatio(Double DSubsidyRatio) {
		this.DSubsidyRatio = DSubsidyRatio;
	}

	public Double getDSubsidyAmount() {
		return this.DSubsidyAmount;
	}

	public void setDSubsidyAmount(Double DSubsidyAmount) {
		this.DSubsidyAmount = DSubsidyAmount;
	}

	public Integer getISubsidyType() {
		return this.ISubsidyType;
	}

	public void setISubsidyType(Integer ISubsidyType) {
		this.ISubsidyType = ISubsidyType;
	}

	public Integer getITerminalType() {
		return this.ITerminalType;
	}

	public void setITerminalType(Integer ITerminalType) {
		this.ITerminalType = ITerminalType;
	}

	public String getSThirdService() {
		return this.SThirdService;
	}

	public void setSThirdService(String SThirdService) {
		this.SThirdService = SThirdService;
	}

	public String getSCategory() {
		return this.SCategory;
	}

	public void setSCategory(String SCategory) {
		this.SCategory = SCategory;
	}

	public String getSSourceMedium() {
		return this.SSourceMedium;
	}

	public void setSSourceMedium(String SSourceMedium) {
		this.SSourceMedium = SSourceMedium;
	}

	public String getSSourceMediumName() {
		return this.SSourceMediumName;
	}

	public void setSSourceMediumName(String SSourceMediumName) {
		this.SSourceMediumName = SSourceMediumName;
	}

	public String getSAdsenseId() {
		return this.SAdsenseId;
	}

	public void setSAdsenseId(String SAdsenseId) {
		this.SAdsenseId = SAdsenseId;
	}

	public String getSAdsenseName() {
		return this.SAdsenseName;
	}

	public void setSAdsenseName(String SAdsenseName) {
		this.SAdsenseName = SAdsenseName;
	}

}