package com.runimg.api.imagedownload.module;

public enum ImageFormat {
	FORMAT_SRC {
		@Override
		String imageFormatToString() {

			return "src";
		}
	},
	FORMAT_JPG {
		@Override
		String imageFormatToString() {

			return "jpg";
		}
	},
	FORMAT_PNG {
		@Override
		String imageFormatToString() {

			return "png";
		}
	},
	FORMAT_WEBP {
		@Override
		String imageFormatToString() {

			return "webp";
		}
	},
	FORMAT_BMP {
		@Override
		String imageFormatToString() {

			return "bmp";
		}
	},
	FORMAT_GIT {
		@Override
		String imageFormatToString() {

			return "git";
		}
	};

	abstract String imageFormatToString();
};