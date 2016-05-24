package com.runimg.api.imagedownload.module;

/**
 * 裁剪方向
 * 
 * @author 3458968481@qq.com
 *
 */
public enum ClipperDirect {
	/** X轴方向 */
	CLIPPER_X {
		@Override
		public String clipperDirectToString() {
			return "x";
		}
	},
	/** Y轴方向 */
	CLIPPER_Y {
		@Override
		public String clipperDirectToString() {
			return "y";
		}
	};

	public abstract String clipperDirectToString();
};