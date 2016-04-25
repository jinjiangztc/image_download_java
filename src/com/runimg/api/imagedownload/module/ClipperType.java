package com.runimg.api.imagedownload.module;

public enum ClipperType {
	SOURCE {
		@Override
		int clipperTypeToInt() {

			return 0;
		}
	},
	MIN_SQUARE {
		@Override
		int clipperTypeToInt() {

			return 1;
		}
	},
	ROUND_RECTANGLE {
		@Override
		int clipperTypeToInt() {

			return 2;
		}
	};

	abstract int clipperTypeToInt();
}