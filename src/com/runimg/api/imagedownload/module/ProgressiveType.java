package com.runimg.api.imagedownload.module;

/**
 * 设置具体jpg格式
 * 
 * @author jinjiangztc@gmail.com
 *
 */
public  enum ProgressiveType {
	ENABLE_PROGRESSIVE {
		@Override
		public
		int progressiveToInt() {
			return 1;
		}
	},
	DISABLE_PROGRESSIVE {
		@Override
		public
		int progressiveToInt() {
			return 0;
		}
	};
	
	public abstract int progressiveToInt();
	};
