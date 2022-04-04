package com.ds.retry;


/**
 *
 * @author hanfeng
 *
 */
public class BtException extends RuntimeException {


    private String code;
    private IErrorEnum errorEnum;

    @SuppressWarnings("unused")
    private BtException() {
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setErrorEnum(IErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }

    protected boolean canEqual(Object other) {
        return other instanceof BtException;
    }

    @Override
    public String toString() {
        return "BusinessException(code=" + getCode() + ", errorEnum=" + getErrorEnum() + ")";
    }

    public String getCode() {
        return this.code;
    }

    public IErrorEnum getErrorEnum() {
        return this.errorEnum;
    }

    public BtException(IErrorEnum errorEnum) {
        super(errorEnum.getDescription());
        this.errorEnum = errorEnum;
        this.code = errorEnum.getCode();
    }

    public BtException(String code, String message) {
        super(message);
        this.code = code;
    }

}
