package com.itopener.framework.base;

/**  
 * @author fuwei.deng
 * @date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
public class BaseRuntimeException extends RuntimeException {

	/** */
	private static final long serialVersionUID = 4347299585092122680L;

	private String code;
	
	public BaseRuntimeException(String code, String message) {
		super(message);
		this.code = code;
	}

	public BaseRuntimeException(String message) {
        super(message);
    }

    public BaseRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseRuntimeException(Throwable cause) {
        super(cause);
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
    
}
