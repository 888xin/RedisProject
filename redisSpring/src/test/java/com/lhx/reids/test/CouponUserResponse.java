package com.lhx.reids.test;



import java.io.Serializable;
import java.util.Date;

public class CouponUserResponse implements Serializable {
    public CouponUserResponse(Long user_id, Long coupon_id, Integer price, Integer range_key,  Date end_time) {
        this.user_id = user_id;
        this.coupon_id = coupon_id;
        this.range_key = range_key;
        this.price = price;
        this.end_time = end_time ;
    }

    @Override
    public String toString() {
        return "CouponUserResponse{" +
                "user_id=" + user_id +
                ", coupon_id=" + coupon_id +
                ", range_key=" + range_key +
                ", price=" + price +
                ", end_time=" + end_time +
                '}';
    }

    private Long id;
    private Long user_id;
    private Long coupon_id;
    /**
     * 优惠券名称
     */
    private String name;
    /**
     * 优惠券的使用比例
     */
    private Double proportion;
    /**
     * 是否使用
     */
    private boolean used;
    
    /**
     * 篮球足球  0足球，1篮球
     */
    private int contestType;
    /**
     * 优惠券使用范围
     */
    private Integer range_key;
    /**
     * 优惠券使用值
     */
    private String range_value;
    /**
     * 开始时间
     */
    private Date start_time;
    /**
     * 结束时间
     */
    private Date end_time;
    /**
     * 面额
     */
    private Integer price;
    /**
     * 更新时间
     */
    private Date update_time;

    /**
     * 是否接受
     */
    private boolean receive;
    
    /**
     * 有效时间
     */
    private Double hour;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getUser_id() {
	return user_id;
    }

    public void setUser_id(Long user_id) {
	this.user_id = user_id;
    }

    public Long getCoupon_id() {
	return coupon_id;
    }

    public Double getHour() {
        return hour;
    }

    public void setHour(Double hour) {
        this.hour = hour;
    }

    public void setCoupon_id(Long coupon_id) {
	this.coupon_id = coupon_id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Double getProportion() {
	return proportion;
    }

    public void setProportion(Double proportion) {
	this.proportion = proportion;
    }

    public boolean isUsed() {
	return used;
    }

    public int getContestType() {
        return contestType;
    }

    public void setContestType(int contestType) {
        this.contestType = contestType;
    }

    public void setUsed(boolean used) {
	this.used = used;
    }

    public Integer getRange_key() {
	return range_key;
    }

    public void setRange_key(Integer range_key) {
	this.range_key = range_key;
    }

    public String getRange_value() {
	return range_value;
    }

    public void setRange_value(String range_value) {
	this.range_value = range_value;
    }

    public Date getStart_time() {
	return start_time;
    }

    public void setStart_time(Date start_time) {
	this.start_time = start_time;
    }

    public Date getEnd_time() {
	return end_time;
    }

    public void setEnd_time(Date end_time) {
	this.end_time = end_time;
    }

    public Integer getPrice() {
	return price;
    }

    public void setPrice(Integer price) {
	this.price = price;
    }

    public Date getUpdate_time() {
	return update_time;
    }

    public void setUpdate_time(Date update_time) {
	this.update_time = update_time;
    }

    public boolean isReceive() {
	return receive;
    }

    public void setReceive(boolean receive) {
	this.receive = receive;
    }
}
