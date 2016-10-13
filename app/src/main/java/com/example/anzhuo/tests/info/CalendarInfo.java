package com.example.anzhuo.tests.info;


public class CalendarInfo {
    /**
     * error_code : 0
     * reason : Success
     * result : {"data":{"holiday":"国庆节","avoid":"嫁娶.造庙.造桥.造船.作灶.安葬.","animalsYear":"猴","desc":"10月1日至7日放假调休，共7天。10月8日（星期六）、10月9日（星期日）上班。","weekday":"星期六","suit":"订盟.纳采.祭祀.祈福.安香.出火.修造.动土.上梁.安门.起基.竖柱.上梁.定磉.开池.移徙.入宅.立券.破土.","lunarYear":"丙申年","lunar":"九月初一","year-month":"2016-10","date":"2016-10-1"}}
     */

    private int error_code;
    private String reason;
    /**
     * data : {"holiday":"国庆节","avoid":"嫁娶.造庙.造桥.造船.作灶.安葬.","animalsYear":"猴","desc":"10月1日至7日放假调休，共7天。10月8日（星期六）、10月9日（星期日）上班。","weekday":"星期六","suit":"订盟.纳采.祭祀.祈福.安香.出火.修造.动土.上梁.安门.起基.竖柱.上梁.定磉.开池.移徙.入宅.立券.破土.","lunarYear":"丙申年","lunar":"九月初一","year-month":"2016-10","date":"2016-10-1"}
     */

    private ResultBean result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * holiday : 国庆节
         * avoid : 嫁娶.造庙.造桥.造船.作灶.安葬.
         * animalsYear : 猴
         * desc : 10月1日至7日放假调休，共7天。10月8日（星期六）、10月9日（星期日）上班。
         * weekday : 星期六
         * suit : 订盟.纳采.祭祀.祈福.安香.出火.修造.动土.上梁.安门.起基.竖柱.上梁.定磉.开池.移徙.入宅.立券.破土.
         * lunarYear : 丙申年
         * lunar : 九月初一
         * year-month : 2016-10
         * date : 2016-10-1
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            private String holiday;
            private String avoid;
            private String animalsYear;
            private String desc;
            private String weekday;
            private String suit;
            private String lunarYear;
            private String lunar;
            private String date;

            public String getHoliday() {
                return holiday;
            }

            public void setHoliday(String holiday) {
                this.holiday = holiday;
            }

            public String getAvoid() {
                return avoid;
            }

            public void setAvoid(String avoid) {
                this.avoid = avoid;
            }

            public String getAnimalsYear() {
                return animalsYear;
            }

            public void setAnimalsYear(String animalsYear) {
                this.animalsYear = animalsYear;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getWeekday() {
                return weekday;
            }

            public void setWeekday(String weekday) {
                this.weekday = weekday;
            }

            public String getSuit() {
                return suit;
            }

            public void setSuit(String suit) {
                this.suit = suit;
            }

            public String getLunarYear() {
                return lunarYear;
            }

            public void setLunarYear(String lunarYear) {
                this.lunarYear = lunarYear;
            }

            public String getLunar() {
                return lunar;
            }

            public void setLunar(String lunar) {
                this.lunar = lunar;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }


//    /**
//     * error_code : 0
//     * reason : Success
//     * result : {"data":{"holiday":"中秋节","avoid":"嫁娶.入宅.安床.出行.","animalsYear":"猴","desc":"9月15日至17日放假调休，共3天。9月18日（星期日）上班。","weekday":"星期四","suit":"祭祀.沐浴.修饰垣墙.平治道涂.余事勿取.","lunarYear":"丙申年","lunar":"八月十五","year-month":"2016-9","date":"2016-9-15"}}
//     */
//
//    private int error_code;
//    private String reason;
//    /**
//     * data : {"holiday":"中秋节","avoid":"嫁娶.入宅.安床.出行.","animalsYear":"猴","desc":"9月15日至17日放假调休，共3天。9月18日（星期日）上班。","weekday":"星期四","suit":"祭祀.沐浴.修饰垣墙.平治道涂.余事勿取.","lunarYear":"丙申年","lunar":"八月十五","year-month":"2016-9","date":"2016-9-15"}
//     */
//
//    private ResultBean result;
//
//    public int getError_code() {
//        return error_code;
//    }
//
//    public void setError_code(int error_code) {
//        this.error_code = error_code;
//    }
//
//    public String getReason() {
//        return reason;
//    }
//
//    public void setReason(String reason) {
//        this.reason = reason;
//    }
//
//    public ResultBean getResult() {
//        return result;
//    }
//
//    public void setResult(ResultBean result) {
//        this.result = result;
//    }
//
//    public static class ResultBean {
//        /**
//         * holiday : 中秋节
//         * avoid : 嫁娶.入宅.安床.出行.
//         * animalsYear : 猴
//         * desc : 9月15日至17日放假调休，共3天。9月18日（星期日）上班。
//         * weekday : 星期四
//         * suit : 祭祀.沐浴.修饰垣墙.平治道涂.余事勿取.
//         * lunarYear : 丙申年
//         * lunar : 八月十五
//         * year-month : 2016-9
//         * date : 2016-9-15
//         */
//
//        private DataBean data;
//
//        public DataBean getData() {
//            return data;
//        }
//
//        public void setData(DataBean data) {
//            this.data = data;
//        }
//
//        public static class DataBean {
//            private String holiday;
//            private String avoid;
//            private String animalsYear;
//            private String desc;
//            private String weekday;
//            private String suit;
//            private String lunarYear;
//            private String lunar;
//            private String date;
//
//            public String getHoliday() {
//                return holiday;
//            }
//
//            public void setHoliday(String holiday) {
//                this.holiday = holiday;
//            }
//
//            public String getAvoid() {
//                return avoid;
//            }
//
//            public void setAvoid(String avoid) {
//                this.avoid = avoid;
//            }
//
//            public String getAnimalsYear() {
//                return animalsYear;
//            }
//
//            public void setAnimalsYear(String animalsYear) {
//                this.animalsYear = animalsYear;
//            }
//
//            public String getDesc() {
//                return desc;
//            }
//
//            public void setDesc(String desc) {
//                this.desc = desc;
//            }
//
//            public String getWeekday() {
//                return weekday;
//            }
//
//            public void setWeekday(String weekday) {
//                this.weekday = weekday;
//            }
//
//            public String getSuit() {
//                return suit;
//            }
//
//            public void setSuit(String suit) {
//                this.suit = suit;
//            }
//
//            public String getLunarYear() {
//                return lunarYear;
//            }
//
//            public void setLunarYear(String lunarYear) {
//                this.lunarYear = lunarYear;
//            }
//
//            public String getLunar() {
//                return lunar;
//            }
//
//            public void setLunar(String lunar) {
//                this.lunar = lunar;
//            }
//
//            public String getDate() {
//                return date;
//            }
//
//            public void setDate(String date) {
//                this.date = date;
//            }
//        }
//    }
}
