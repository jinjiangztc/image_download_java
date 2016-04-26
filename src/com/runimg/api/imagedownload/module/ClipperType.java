package com.runimg.api.imagedownload.module;

public enum ClipperType {
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