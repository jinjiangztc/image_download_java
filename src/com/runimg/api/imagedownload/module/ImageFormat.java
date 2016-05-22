package com.runimg.api.imagedownload.module;

/**
 * 图片格式
 * 
 * @author jinjiangztc@gmail.com
 *
 */
public enum ImageFormat {
	FORMAT_SRC {
		@Override
		public String imageFormatToString() {

			return "src";
		}
	},
	FORMAT_JPG {
		@Override
		public String imageFormatToString() {

			return "jpg";
		}
	},
	FORMAT_PNG {
		@Override
		public String imageFormatToString() {

			return "png";
		}
	},
	FORMAT_WEBP {
		@Override
		public String imageFormatToString() {

			return "webp";
		}
	},
	FORMAT_BMP {
		@Override
		public String imageFormatToString() {

			return "bmp";
		}
	},
	FORMAT_GIT {
		@Override
		public String imageFormatToString() {

			return "gif";
		}
	};

	public abstract String imageFormatToString();
};