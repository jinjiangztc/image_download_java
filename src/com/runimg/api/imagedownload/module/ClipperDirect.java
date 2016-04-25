package com.runimg.api.imagedownload.module;

public enum ClipperDirect {
	CLIPPER_X {
		@Override
		String clipperDirectToString() {
			return "x";
		}
	},
	CLIPPER_Y {
		@Override
		String clipperDirectToString() {
			return "y";
		}
	};

	abstract String clipperDirectToString();
};