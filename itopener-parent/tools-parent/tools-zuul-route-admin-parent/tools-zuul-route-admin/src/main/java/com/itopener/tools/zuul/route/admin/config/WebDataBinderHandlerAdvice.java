package com.itopener.tools.zuul.route.admin.config;

import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class WebDataBinderHandlerAdvice {

	@InitBinder
	// 此处的参数也可以是ServletRequestDataBinder类型
	public void initBinder(WebDataBinder binder) throws Exception {
		// 注册自定义的属性编辑器
		binder.registerCustomEditor(int.class, new IntEditor());
		binder.registerCustomEditor(float.class, new FloatEditor());
		binder.registerCustomEditor(long.class, new LongEditor());
		binder.registerCustomEditor(double.class, new DoubleEditor());
		binder.registerCustomEditor(byte.class, new ByteEditor());
		binder.registerCustomEditor(boolean.class, new BooleanEditor());
	}

	private static class IntEditor extends CustomNumberEditor {
		public IntEditor() {
			super(Integer.class, true);
		}

		@Override
		public void setValue(Object value) {
			if (value == null) {
				super.setValue(0);
			} else {
				super.setValue(value);
			}
		}
	}

	private static class LongEditor extends CustomNumberEditor {
		public LongEditor() {
			super(Long.class, true);
		}

		@Override
		public void setValue(Object value) {
			if (value == null) {
				super.setValue(0);
			} else {
				super.setValue(value);
			}
		}
	}

	private static class FloatEditor extends CustomNumberEditor {
		public FloatEditor() {
			super(Float.class, true);
		}

		@Override
		public void setValue(Object value) {
			if (value == null) {
				super.setValue(0);
			} else {
				super.setValue(value);
			}
		}
	}

	private static class DoubleEditor extends CustomNumberEditor {
		public DoubleEditor() {
			super(Double.class, true);
		}

		@Override
		public void setValue(Object value) {
			if (value == null) {
				super.setValue(0);
			} else {
				super.setValue(value);
			}
		}
	}

	private static class ByteEditor extends CustomNumberEditor {
		public ByteEditor() {
			super(Byte.class, true);
		}

		@Override
		public void setValue(Object value) {
			if (value == null) {
				super.setValue(0);
			} else {
				super.setValue(value);
			}
		}
	}
	
	private static class BooleanEditor extends CustomBooleanEditor {
		public BooleanEditor() {
			super(true);
		}
		
		@Override
		public void setValue(Object value) {
			if (value == null) {
				super.setValue(false);
			} else {
				super.setValue(value);
			}
		}
	}
	
}
