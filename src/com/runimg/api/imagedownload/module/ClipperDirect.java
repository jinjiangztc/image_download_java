package com.runimg.api.imagedownload.module;

public enum ClipperDirect {
	CLIPPER_X {
		@Override
		public String clipperDirectToString() {
			return "x";
		}
	},
	CLIPPER_Y {
		@Override
		public String clipperDirectToString() {
			return "y";
		}
	};

	public abstract String clipperDirectToString();
};