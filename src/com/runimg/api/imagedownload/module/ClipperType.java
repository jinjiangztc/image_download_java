package com.runimg.api.imagedownload.module;
/**
 * 裁剪类型
 * 
 * @author jinjiangztc@gmail.com
 *
 */
public enum ClipperType {
	/**内切圆裁剪 */
	SOURCE {
		@Override
		public int clipperTypeToInt() {

			return 0;
		}
	},
	MIN_SQUARE {
		@Override
		public int clipperTypeToInt() {

			return 1;
		}
	},
	ROUND_RECTANGLE {
		@Override
		public int clipperTypeToInt() {

			return 2;
		}
	};

	public abstract int clipperTypeToInt();
}