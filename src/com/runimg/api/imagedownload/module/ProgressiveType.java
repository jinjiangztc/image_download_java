package com.runimg.api.imagedownload.module;

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
