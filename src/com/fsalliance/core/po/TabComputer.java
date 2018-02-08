package com.fsalliance.core.po;

import java.sql.Timestamp;


/**
 * TabComputer entity. @author MyEclipse Persistence Tools
 */

public class TabComputer  implements java.io.Serializable {


    // Fields    

     private String SId;
     private String SName;
     private String SIp;
     private Timestamp dtStarttime;
     private Timestamp dtEndtime;
     private Long IType;
     private String SUser;


    // Constructors

    /** default constructor */
    public TabComputer() {
    }

	/** minimal constructor */
    public TabComputer(String SId, String SName, String SIp) {
        this.SId = SId;
        this.SName = SName;
        this.SIp = SIp;
    }
    
    /** full constructor */
    public TabComputer(String SId, String SName, String SIp, Timestamp dtStarttime, Timestamp dtEndtime, Long IType, String SUser) {
        this.SId = SId;
        this.SName = SName;
        this.SIp = SIp;
        this.dtStarttime = dtStarttime;
        this.dtEndtime = dtEndtime;
        this.IType = IType;
        this.SUser = SUser;
    }

   
    // Property accessors

    public String getSId() {
        return this.SId;
    }
    
    public void setSId(String SId) {
        this.SId = SId;
    }

    public String getSName() {
        return this.SName;
    }
    
    public void setSName(String SName) {
        this.SName = SName;
    }

    public String getSIp() {
        return this.SIp;
    }
    
    public void setSIp(String SIp) {
        this.SIp = SIp;
    }

    public Timestamp getDtStarttime() {
        return this.dtStarttime;
    }
    
    public void setDtStarttime(Timestamp dtStarttime) {
        this.dtStarttime = dtStarttime;
    }

    public Timestamp getDtEndtime() {
        return this.dtEndtime;
    }
    
    public void setDtEndtime(Timestamp dtEndtime) {
        this.dtEndtime = dtEndtime;
    }

    public Long getIType() {
        return this.IType;
    }
    
    public void setIType(Long IType) {
        this.IType = IType;
    }

    public String getSUser() {
        return this.SUser;
    }
    
    public void setSUser(String SUser) {
        this.SUser = SUser;
    }
   








}