package com.runimg.api.imagedownload.module;

public enum ImageType {
	TYPE_1D {
		@Override
		String imageTypeToString() {
			return "1d";
		}
	},
	TYPE_2D {
		@Override
		String imageTypeToString() {
			return "2d";
		}
	},
	TYPE_2D_0_0 {
		@Override
		String imageTypeToString() {
			
			return "2d_0_0";
		}
	},
	TYPE_2D_0_1 {
		@Override
		String imageTypeToString() {
			
			return "2d_0_1";
		}
	},
	TYPE_2D_1_0 {
		@Override
		String imageTypeToString() {
			
			return "2d_1_0";
		}
	},
	TYPE_2D_1_1 {
		@Override
		String imageTypeToString() {
			return "2d_1_1";
		}
	},
	TYPE_4D {
		@Override
		String imageTypeToString() {
			return "4d";
		}
	},
	TYPE_4D_0_0 {
		@Override
		String imageTypeToString() {
			return "4d_0_0";
		}
	},
	TYPE_4D_0_1 {
		@Override
		String imageTypeToString() {
			return "4d_0_1";
		}
	},
	TYPE_4D_0_2 {
		@Override
		String imageTypeToString() {
			return "4d_0_2";
		}
	},
	TYPE_4D_0_3 {
		@Override
		String imageTypeToString() {
			return "4d_0_3";
		}
	},
	TYPE_4D_1_0 {
		@Override
		String imageTypeToString() {
			return "4d_1_0";
		}
	},
	TYPE_4D_1_1 {
		@Override
		String imageTypeToString() {
			return "4d_1_1";
		}
	},
	TYPE_4D_1_2 {
		@Override
		String imageTypeToString() {
			return "4d_1_2";
		}
	},
	TYPE_4D_1_3 {
		@Override
		String imageTypeToString() {
			
			return "4d_1_3";
		}
	},
	TYPE_4D_2_0 {
		@Override
		String imageTypeToString() {
			
			return "4d_2_0";
		}
	},
	TYPE_4D_2_1 {
		@Override
		String imageTypeToString() {
			
			return "4d_2_1";
		}
	},
	TYPE_4D_2_2 {
		@Override
		String imageTypeToString() {
			
			return "4d_2_2";
		}
	},
	TYPE_4D_2_3 {
		@Override
		String imageTypeToString() {
			
			return "4d_2_3";
		}
	},
	TYPE_4D_3_0 {
		@Override
		String imageTypeToString() {
			
			return "4d_3_0";
		}
	},
	TYPE_4D_3_1 {
		@Override
		String imageTypeToString() {
			
			return "4d_3_1";
		}
	},
	TYPE_4D_3_2 {
		@Override
		String imageTypeToString() {
			
			return "4d_3_2";
		}
	},
	TYPE_4D_3_3 {
		@Override
		String imageTypeToString() {
			
			return "4d_3_3";
		}
	},
	TYPE_HI_RES_800 {
		@Override
		String imageTypeToString() {
			
			return "hi_res_800";
		}
	},
	TYPE_HI_RES_5000 {
		@Override
		String imageTypeToString() {
			
			return "hi_res_5000";
		}
	};

	abstract String imageTypeToString();
};