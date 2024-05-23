package com.ppp.billing.model.dto;

public class JobActivityOptionDTO {
		
	private int xPerforated;
	private int xNumbered;
	private int lamination;
	private int xCreased;
	private int xWiredStiched;
	private int xCross;
	private String glueOption;
	private int bindingType;
	private boolean handgather;
	private boolean stitching;
	private boolean trimmed;
	private boolean selloptaped;
	private boolean sewn;
	public int getxPerforated() {
		return xPerforated;
	}
	public void setxPerforated(int xPerforated) {
		this.xPerforated = xPerforated;
	}
	public int getxNumbered() {
		return xNumbered;
	}
	public void setxNumbered(int xNumbered) {
		this.xNumbered = xNumbered;
	}
	public int getLamination() {
		return lamination;
	}
	public void setLamination(int lamination) {
		this.lamination = lamination;
	}
	public int getxCreased() {
		return xCreased;
	}
	public void setxCreased(int xCreased) {
		this.xCreased = xCreased;
	}
	public int getxWiredStiched() {
		return xWiredStiched;
	}
	public void setxWiredStiched(int xWiredStiched) {
		this.xWiredStiched = xWiredStiched;
	}
	public int getxCross() {
		return xCross;
	}
	public void setxCross(int xCross) {
		this.xCross = xCross;
	}
	public String getGlueOption() {
		return glueOption;
	}
	public void setGlueOption(String glueOption) {
		this.glueOption = glueOption;
	}

	public boolean isHandgather() {
		return handgather;
	}
	public void setHandgather(boolean handgather) {
		this.handgather = handgather;
	}
	public boolean isStitching() {
		return stitching;
	}
	public void setStitching(boolean stitching) {
		this.stitching = stitching;
	}
	public boolean isTrimmed() {
		return trimmed;
	}
	public void setTrimmed(boolean trimmed) {
		this.trimmed = trimmed;
	}
	public boolean isSelloptaped() {
		return selloptaped;
	}
	public void setSelloptaped(boolean selloptaped) {
		this.selloptaped = selloptaped;
	}
	public boolean isSewn() {
		return sewn;
	}
	public void setSewn(boolean sewn) {
		this.sewn = sewn;
	}
	public int getBindingType() {
		return bindingType;
	}
	public void setBindingType(int bindingType) {
		this.bindingType = bindingType;
	}

	
}
