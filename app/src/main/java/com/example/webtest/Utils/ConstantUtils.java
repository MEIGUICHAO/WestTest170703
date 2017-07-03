package com.example.webtest.Utils;

import android.util.Log;

import com.example.webtest.base.ConstantValue;

/**
 * Created by MGC on 17/5/16.
 */

public class ConstantUtils {


    public static int autoSame20 = 27;
    public static int autoSame15 = 34;
    public static int autoSame10 = 44;
    public static int originAmount = 1;
    public static int AllCountCoordinate = 1;

    public static boolean IS_SC = false;

    public static boolean isSc() {
        return IS_SC;
    }

    public static void setIsSc(boolean isSc) {
        IS_SC = isSc;
    }

    public static boolean isCUSTOM() {
        return CUSTOM;
    }

    public static void setCUSTOM(boolean CUSTOM) {
        ConstantUtils.CUSTOM = CUSTOM;
    }

    public static boolean CUSTOM = false;

    //blank
    public static int autoBlank20 = 20;
    public static int autoBlank15 = 15;
    public static int autoBlank10 = 10;

    public static int getAutoSame20() {
        return autoSame20;
    }

    public static void setAutoSame20(String num) {
        autoSame20 = Integer.parseInt(num);

    }

    public static int getAutoSame15() {
        return autoSame15;
    }

    public static void setAutoSame15(String num) {
        autoSame15 = Integer.parseInt(num);
    }

    public static int getAutoSame10() {
        return autoSame10;
    }

    public static void setAutoSame10(String num) {
        autoSame10 = Integer.parseInt(num);
    }

    public static int getOriginAmount() {
        return originAmount;
    }

    public static void setOriginAmount(String num) {
        originAmount = Integer.parseInt(num);
    }

    public static int getAllCountCoordinate() {
        return AllCountCoordinate;
    }

    public static void setAllCountCoordinate(String num) {
        AllCountCoordinate = Integer.parseInt(num);
    }

    public static int getAutoBlank20() {
        return autoBlank20;
    }

    public static void setAutoBlank20(String num) {
        autoBlank20 = Integer.parseInt(num);
    }

    public static int getAutoBlank15() {
        return autoBlank15;
    }

    public static void setAutoBlank15(String num) {
        autoBlank15 = Integer.parseInt(num);
    }

    public static int getAutoBlank10() {
        return autoBlank10;
    }

    public static void setAutoBlank10(String num) {
        autoBlank10 = Integer.parseInt(num);
    }


/*
* boolean
* */


    public static boolean SC_20BIGIN = false;
    public static boolean SC_15BIGIN = false;
    public static boolean SC_10BIGIN = false;

    public static boolean FT_20BIGIN = false;
    public static boolean FT_15BIGIN = false;
    public static boolean FT_10BIGIN = false;

    public static boolean SC_20BIGGER_BIGIN = false;
    public static boolean SC_15BIGGER_BIGIN = false;
    public static boolean SC_10BIGGER_BIGIN = false;


    public static boolean FT_20BIGGRE_BIGIN = false;
    public static boolean FT_15BIGGRE_BIGIN = false;
    public static boolean FT_10BIGGRE_BIGIN = false;

    public static int SC_20Fabint = -1;
    public static int SC_15Fabint = -1;
    public static int SC_10Fabint = -1;

    public static int FT_20Fabint = -1;
    public static int FT_15Fabint = -1;
    public static int FT_10Fabint = -1;

    public static int SC_20BIGGER_Fabint = -1;
    public static int SC_15BIGGER_Fabint = -1;
    public static int SC_10BIGGER_Fabint = -1;


    public static int FT_20BIGGRE_Fabint = -1;
    public static int FT_15BIGGRE_Fabint = -1;
    public static int FT_10BIGGRE_Fabint = -1;

    public static String SC20CTREM = "";
    public static String SC15CTREM = "";
    public static String SC10CTREM = "";

    public static String FT20CTREM = "";
    public static String FT15CTREM = "";
    public static String FT10CTREM = "";

    public static String FT20BIGGERCTREM = "";
    public static String FT15BIGGERCTREM = "";
    public static String FT10BIGGERCTREM = "";

    public static String SC20BIGGERCTREM = "";
    public static String SC15BIGGERCTREM = "";
    public static String SC10BIGGERCTREM = "";


    public static void setScBigin(boolean scBigin, int type) {

        switch (type) {
            case ConstantValue.TYPE_BLANK_20:
                SC_20BIGIN = scBigin;
                if (!scBigin) {
                    SC_20Fabint = -1;
                }
                break;
            case ConstantValue.TYPE_BLANK_15:
                SC_15BIGIN = scBigin;
                if (!scBigin) {
                    SC_15Fabint = -1;
                }
                break;
            case ConstantValue.TYPE_BLANK_10:
                SC_10BIGIN = scBigin;
                if (!scBigin) {
                    SC_10Fabint = -1;
                }
                break;
        }
    }

    public static void setFtBigin(boolean ftBigin, int type) {

        switch (type) {
            case ConstantValue.TYPE_BLANK_20:
                FT_20BIGIN = ftBigin;
                if (!ftBigin) {
                    FT_20Fabint = -1;
                }
                break;
            case ConstantValue.TYPE_BLANK_15:
                FT_15BIGIN = ftBigin;
                if (!ftBigin) {
                    FT_15Fabint = -1;
                }
                break;
            case ConstantValue.TYPE_BLANK_10:
                FT_10BIGIN = ftBigin;
                if (!ftBigin) {
                    FT_10Fabint = -1;
                }
                break;
        }
    }

    public static void setScBiggerBigin(boolean scBiggerBigin, int type) {

        switch (type) {
            case ConstantValue.TYPE_BLANK_20:
                SC_20BIGGER_BIGIN = scBiggerBigin;
                if (!scBiggerBigin) {
                    SC_20BIGGER_Fabint = -1;
                }
                break;
            case ConstantValue.TYPE_BLANK_15:
                SC_15BIGGER_BIGIN = scBiggerBigin;
                if (!scBiggerBigin) {
                    SC_15BIGGER_Fabint = -1;
                }
                break;
            case ConstantValue.TYPE_BLANK_10:
                SC_10BIGGER_BIGIN = scBiggerBigin;
                if (!scBiggerBigin) {
                    SC_10BIGGER_Fabint = -1;
                }
                break;
        }
    }

    public static void setFtBiggreBigin(boolean ftBiggreBigin, int type) {

        switch (type) {
            case ConstantValue.TYPE_BLANK_20:
                FT_20BIGGRE_BIGIN = ftBiggreBigin;
                if (!ftBiggreBigin) {
                    FT_20BIGGRE_Fabint = -1;
                }
                break;
            case ConstantValue.TYPE_BLANK_15:
                FT_15BIGGRE_BIGIN = ftBiggreBigin;
                if (!ftBiggreBigin) {
                    FT_15BIGGRE_Fabint = -1;
                }
                break;
            case ConstantValue.TYPE_BLANK_10:
                FT_10BIGGRE_BIGIN = ftBiggreBigin;
                if (!ftBiggreBigin) {
                    FT_10BIGGRE_Fabint = -1;
                }
                break;
        }
    }


    public static boolean isFtBigin(int type) {
        boolean mBoolean = false;
        switch (type) {
            case ConstantValue.TYPE_BLANK_20:
                mBoolean = FT_20BIGIN;
                if (FT_20Fabint == -1) {
                    mBoolean = false;
                }
                break;
            case ConstantValue.TYPE_BLANK_15:
                mBoolean = FT_15BIGIN;
                if (FT_15Fabint == -1) {
                    mBoolean = false;
                }
                break;
            case ConstantValue.TYPE_BLANK_10:
                mBoolean = FT_10BIGIN;
                if (FT_10Fabint == -1) {
                    mBoolean = false;
                }
                break;
        }
        return mBoolean;
    }


    public static boolean isScBigin(int type) {

        boolean mBoolean = false;
        switch (type) {
            case ConstantValue.TYPE_BLANK_20:
                mBoolean = SC_20BIGIN;
                if (SC_20Fabint == -1) {
                    mBoolean = false;
                }
                break;
            case ConstantValue.TYPE_BLANK_15:
                mBoolean = SC_15BIGIN;
                if (SC_15Fabint == -1) {
                    mBoolean = false;
                }
                break;
            case ConstantValue.TYPE_BLANK_10:
                mBoolean = SC_10BIGIN;
                if (SC_10Fabint == -1) {
                    mBoolean = false;
                }
                break;
        }
        return mBoolean;
    }

    public static boolean isFtBiggreBigin(int type) {

        boolean mBoolean = false;
        switch (type) {
            case ConstantValue.TYPE_BLANK_20:
                mBoolean = FT_20BIGGRE_BIGIN;
                if (FT_20BIGGRE_Fabint == -1) {
                    mBoolean = false;
                }
                break;
            case ConstantValue.TYPE_BLANK_15:
                mBoolean = FT_15BIGGRE_BIGIN;

                if (FT_15BIGGRE_Fabint == -1) {
                    mBoolean = false;
                }
                break;
            case ConstantValue.TYPE_BLANK_10:
                mBoolean = FT_10BIGGRE_BIGIN;


                if (FT_10BIGGRE_Fabint == -1) {
                    mBoolean = false;
                }
                break;
        }
        return mBoolean;
    }


    public static boolean isScBiggerBigin(int type) {

        boolean mBoolean = false;
        switch (type) {
            case ConstantValue.TYPE_BLANK_20:
                mBoolean = SC_20BIGGER_BIGIN;
                if (SC_20BIGGER_Fabint == -1) {
                    mBoolean = false;
                }
                break;
            case ConstantValue.TYPE_BLANK_15:
                mBoolean = SC_15BIGGER_BIGIN;

                if (SC_15BIGGER_Fabint == -1) {
                    mBoolean = false;
                }
                break;
            case ConstantValue.TYPE_BLANK_10:
                mBoolean = SC_10BIGGER_BIGIN;

                if (SC_10BIGGER_Fabint == -1) {
                    mBoolean = false;
                }
                break;
        }
        return mBoolean;
    }


    public static void setFtFab(int blanktype, String ctrem) {
        switch (blanktype) {
            case ConstantValue.TYPE_BLANK_20:
                if (FT_20Fabint == -1) {
                    FT_20Fabint = 0;
                    FT20CTREM = ctrem;
                } else if (!FT20CTREM.equals(ctrem) && FT_20Fabint <= ConstantValue.fabMax && FT_20Fabint != -1) {
                    FT_20Fabint++;
                } else if (!FT20CTREM.equals(ctrem) && FT_20Fabint != -1) {
                    FT_20Fabint = ConstantValue.fabMax;
                }
                break;
            case ConstantValue.TYPE_BLANK_15:
                if (FT_15Fabint == -1) {
                    FT_15Fabint = 0;
                    FT15CTREM = ctrem;
                } else if (!FT15CTREM.equals(ctrem) && FT_15Fabint <= ConstantValue.fabMax && FT_15Fabint != -1) {
                    FT_15Fabint++;
                } else if (!FT15CTREM.equals(ctrem) && FT_15Fabint != -1) {
                    FT_15Fabint = ConstantValue.fabMax;
                }
                break;
            case ConstantValue.TYPE_BLANK_10:
                if (FT_10Fabint == -1) {
                    FT_10Fabint = 0;
                    FT10CTREM = ctrem;
                } else if (!FT10CTREM.equals(ctrem) && FT_10Fabint <= ConstantValue.fabMax && FT_10Fabint != -1) {
                    FT_10Fabint++;
                } else if (!FT10CTREM.equals(ctrem) && FT_10Fabint != -1) {
                    FT_10Fabint = ConstantValue.fabMax;
                }
                break;
        }
    }

    public static void setScFab(int blanktype, String ctrem) {
        switch (blanktype) {
            case ConstantValue.TYPE_BLANK_20:
                if (SC_20Fabint == -1) {
                    SC_20Fabint = 0;
                    SC20CTREM = ctrem;
                } else if (!SC20CTREM.equals(ctrem) && SC_20Fabint <= ConstantValue.fabMax && SC_20Fabint != -1) {
                    SC_20Fabint++;
                } else if (!SC20CTREM.equals(ctrem) && SC_20Fabint != -1) {
                    SC_20Fabint = ConstantValue.fabMax;
                }
                break;
            case ConstantValue.TYPE_BLANK_15:
                if (SC_15Fabint == -1) {
                    SC_15Fabint = 0;
                    SC15CTREM = ctrem;
                } else if (!SC15CTREM.equals(ctrem) && SC_15Fabint <= ConstantValue.fabMax && SC_15Fabint != -1) {
                    SC_15Fabint++;
                } else if (!SC15CTREM.equals(ctrem) && SC_15Fabint != -1) {
                    SC_15Fabint = ConstantValue.fabMax;
                }
                break;
            case ConstantValue.TYPE_BLANK_10:
                if (SC_10Fabint == -1) {
                    SC_10Fabint = 0;
                    SC10CTREM = ctrem;
                    Log.e(ctrem, "ScFabctrem: " + ctrem);
                } else if (!SC10CTREM.equals(ctrem) && SC_10Fabint <= ConstantValue.fabMax && SC_10Fabint != -1) {
                    SC_10Fabint++;
                    Log.e(ctrem, "SC_10Fabint: " + SC_10Fabint);
                } else if (!SC10CTREM.equals(ctrem) && SC_10Fabint != -1) {
                    SC_10Fabint = ConstantValue.fabMax;
                    Log.e(ctrem, "fabMax: " + SC_10Fabint);
                }
                break;
        }
    }

    public static void setScBiggerFab(int blanktype, String ctrem) {
        switch (blanktype) {
            case ConstantValue.TYPE_BLANK_20:
                if (SC_20BIGGER_Fabint == -1) {
                    SC_20BIGGER_Fabint = 0;
                    SC20BIGGERCTREM = ctrem;
                } else if (!SC20BIGGERCTREM.equals(ctrem) && SC_20BIGGER_Fabint <= ConstantValue.fabMax && SC_20BIGGER_Fabint != -1) {
                    SC_20BIGGER_Fabint++;
                } else if (!SC20BIGGERCTREM.equals(ctrem) && SC_20BIGGER_Fabint != -1) {
                    SC_20BIGGER_Fabint = ConstantValue.fabMax;
                }
                break;
            case ConstantValue.TYPE_BLANK_15:
                if (SC_15BIGGER_Fabint == -1) {
                    SC_15BIGGER_Fabint = 0;
                    SC15BIGGERCTREM = ctrem;
                } else if (!SC15BIGGERCTREM.equals(ctrem) && SC_15BIGGER_Fabint <= ConstantValue.fabMax && SC_15BIGGER_Fabint != -1) {
                    SC_15BIGGER_Fabint++;
                } else if (!SC15BIGGERCTREM.equals(ctrem) && SC_15BIGGER_Fabint != -1) {
                    SC_15BIGGER_Fabint = ConstantValue.fabMax;
                }
                break;
            case ConstantValue.TYPE_BLANK_10:
                if (SC_10BIGGER_Fabint == -1) {
                    SC_10BIGGER_Fabint = 0;
                    SC10BIGGERCTREM = ctrem;
                } else if (!SC10BIGGERCTREM.equals(ctrem) && SC_10BIGGER_Fabint <= ConstantValue.fabMax && SC_10BIGGER_Fabint != -1) {
                    SC_10BIGGER_Fabint++;
                } else if (!SC10BIGGERCTREM.equals(ctrem) && SC_10BIGGER_Fabint != -1) {
                    SC_10BIGGER_Fabint = ConstantValue.fabMax;
                }
                break;
        }
    }

    public static void setFtBiggerFab(int blanktype, String ctrem) {
        switch (blanktype) {
            case ConstantValue.TYPE_BLANK_20:
                if (FT_20BIGGRE_Fabint == -1) {
                    FT_20BIGGRE_Fabint = 0;
                    FT20BIGGERCTREM = ctrem;
                } else if (!FT20BIGGERCTREM.equals(ctrem) && FT_20BIGGRE_Fabint <= ConstantValue.fabMax && FT_20BIGGRE_Fabint != -1) {
                    FT_20BIGGRE_Fabint++;
                } else if (!FT20BIGGERCTREM.equals(ctrem) && FT_20BIGGRE_Fabint != -1) {
                    FT_20BIGGRE_Fabint = ConstantValue.fabMax;
                }
                break;
            case ConstantValue.TYPE_BLANK_15:
                if (FT_15BIGGRE_Fabint == -1) {
                    FT_15BIGGRE_Fabint = 0;
                    FT15BIGGERCTREM = ctrem;
                } else if (!FT15BIGGERCTREM.equals(ctrem) && FT_15BIGGRE_Fabint <= ConstantValue.fabMax && FT_15BIGGRE_Fabint != -1) {
                    FT_15BIGGRE_Fabint++;
                } else if (!FT15BIGGERCTREM.equals(ctrem) && FT_15BIGGRE_Fabint != -1) {
                    FT_15BIGGRE_Fabint = ConstantValue.fabMax;
                }
                break;
            case ConstantValue.TYPE_BLANK_10:
                if (FT_10BIGGRE_Fabint == -1) {
                    FT_10BIGGRE_Fabint = 0;
                    FT10BIGGERCTREM = ctrem;
                } else if (!FT10BIGGERCTREM.equals(ctrem) && FT_10BIGGRE_Fabint <= ConstantValue.fabMax && FT_10BIGGRE_Fabint != -1) {
                    FT_10BIGGRE_Fabint++;
                } else if (!FT10BIGGERCTREM.equals(ctrem) && FT_10BIGGRE_Fabint != -1) {
                    FT_10BIGGRE_Fabint = ConstantValue.fabMax;
                }
                break;
        }
    }

    public static int getFabInt(boolean is_sc, boolean isbigger, int type) {
        int fabint = 0;
        if (is_sc && !isbigger) {
            switch (type) {
                case ConstantValue.TYPE_BLANK_20:
                    fabint = SC_20Fabint;
                    break;
                case ConstantValue.TYPE_BLANK_15:
                    fabint = SC_15Fabint;
                    break;
                case ConstantValue.TYPE_BLANK_10:
                    fabint = SC_10Fabint;
                    break;
            }
        } else if (is_sc && isbigger) {
            switch (type) {
                case ConstantValue.TYPE_BLANK_20:
                    fabint = SC_20BIGGER_Fabint;
                    break;
                case ConstantValue.TYPE_BLANK_15:
                    fabint = SC_15BIGGER_Fabint;
                    break;
                case ConstantValue.TYPE_BLANK_10:
                    fabint = SC_10BIGGER_Fabint;
                    break;
            }
        } else if (!is_sc && !isbigger) {
            switch (type) {
                case ConstantValue.TYPE_BLANK_20:
                    fabint = FT_20Fabint;
                    break;
                case ConstantValue.TYPE_BLANK_15:
                    fabint = FT_15Fabint;
                    break;
                case ConstantValue.TYPE_BLANK_10:
                    fabint = FT_10Fabint;
                    break;
            }
        } else if (!is_sc && isbigger) {
            switch (type) {
                case ConstantValue.TYPE_BLANK_20:
                    fabint = FT_20BIGGRE_Fabint;
                    break;
                case ConstantValue.TYPE_BLANK_15:
                    fabint = FT_15BIGGRE_Fabint;
                    break;
                case ConstantValue.TYPE_BLANK_10:
                    fabint = FT_10BIGGRE_Fabint;
                    break;
            }
        }
        if (fabint == -1) {
            fabint = 0;
        }
//        fabint = fabint + 1;
        return fabint;
    }
}
