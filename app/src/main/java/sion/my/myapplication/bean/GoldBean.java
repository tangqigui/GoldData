package sion.my.myapplication.bean;

import java.util.List;

public class GoldBean {
    @Override
    public String toString() {
        return "GoldBean{" +
                "msg='" + msg + '\'' +
                ", retCode='" + retCode + '\'' +
                ", result=" + result +
                '}';
    }

    /**
     * msg : success
     * result : [{"closePri":"299.99","highPic":"301.45","limit":"-0.14%","lowPic":"299.60","name":"沪金9999","openPri":"301.45","time":"2019-06-13 21:37:24","totalTurnover":"570638000.00","totalVol":"1902.00","variety":"AU99.99","yesDayPic":"300.41"},{"closePri":"--","highPic":"--","limit":"--","lowPic":"--","name":"黄金995","openPri":"--","time":"2019-06-13 19:50:06","totalTurnover":"--","totalVol":"--","variety":"AU995","yesDayPic":"271.60"},{"closePri":"300.05","highPic":"300.64","limit":"-0.11%","lowPic":"299.81","name":"黄金延期","openPri":"300.40","time":"2019-06-13 21:37:29","totalTurnover":"3979778500.00","totalVol":"13254.00","variety":"Au(T+D)","yesDayPic":"299.85"},{"closePri":"--","highPic":"--","limit":"--","lowPic":"--","name":"延期单金","openPri":"--","time":"2019-06-13 21:37:17","totalTurnover":"--","totalVol":"--","variety":"Au(T+N1)","yesDayPic":"299.85"},{"closePri":"302.75","highPic":"302.75","limit":"-0.05%","lowPic":"302.40","name":"延期双金","openPri":"302.55","time":"2019-06-13 21:37:17","totalTurnover":"16940000.00","totalVol":"56.00","variety":"Au(T+N2)","yesDayPic":"302.70"},{"closePri":"--","highPic":"--","limit":"--","lowPic":"--","name":"沪金100G","openPri":"--","time":"2019-06-13 21:34:59","totalTurnover":"--","totalVol":"--","variety":"Au100g","yesDayPic":"300.19"},{"closePri":"--","highPic":"--","limit":"--","lowPic":"--","name":"沪 条50G","openPri":"--","time":"2019-06-13 19:50:06","totalTurnover":"--","totalVol":"--","variety":"Au50g","yesDayPic":"255.00"},{"closePri":"299.90","highPic":"299.90","limit":"-0.08%","lowPic":"299.90","name":"沪  金95","openPri":"299.90","time":"2019-06-13 21:37:27","totalTurnover":"599800.00","totalVol":"2.00","variety":"Au99.95","yesDayPic":"300.14"},{"closePri":"299.99","highPic":"301.45","limit":"-0.14%","lowPic":"299.60","name":"沪  金99","openPri":"301.45","time":"2019-06-13 21:37:24","totalTurnover":"57063800.80","totalVol":"190.00","variety":"Au99.99","yesDayPic":"300.41"},{"closePri":"--","highPic":"--","limit":"--","lowPic":"--","name":"I黄金100G","openPri":"--","time":"2019-06-13 20:02:42","totalTurnover":"--","totalVol":"--","variety":"IAU100G","yesDayPic":"280.00"},{"closePri":"--","highPic":"--","limit":"--","lowPic":"--","name":"I黄金995","openPri":"--","time":"2019-06-13 19:50:06","totalTurnover":"--","totalVol":"--","variety":"IAU99.5","yesDayPic":"237.80"},{"closePri":"297.00","highPic":"297.00","limit":"-0.20%","lowPic":"297.00","name":"I黄金9999","openPri":"297.00","time":"2019-06-13 21:37:30","totalTurnover":"5940000.00","totalVol":"20.00","variety":"IAU99.99","yesDayPic":"297.60"},{"closePri":"300.05","highPic":"300.56","limit":"-0.10%","lowPic":"299.86","name":"M黄金延期","openPri":"300.35","time":"2019-06-13 21:37:28","totalTurnover":"2869091000.00","totalVol":"9556.00","variety":"MAUTD","yesDayPic":"299.83"},{"closePri":"--","highPic":"--","limit":"--","lowPic":"--","name":"沪  铂95","openPri":"--","time":"2019-06-13 21:32:45","totalTurnover":"--","totalVol":"--","variety":"Pt99.95","yesDayPic":"187.31"}]
     * retCode : 200
     */

    public String msg;
    public String retCode;
    public List<ResultBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        @Override
        public String toString() {
            return "ResultBean{" +
                    "closePri='" + closePri + '\'' +
                    ", highPic='" + highPic + '\'' +
                    ", limit='" + limit + '\'' +
                    ", lowPic='" + lowPic + '\'' +
                    ", name='" + name + '\'' +
                    ", openPri='" + openPri + '\'' +
                    ", time='" + time + '\'' +
                    ", totalTurnover='" + totalTurnover + '\'' +
                    ", totalVol='" + totalVol + '\'' +
                    ", variety='" + variety + '\'' +
                    ", yesDayPic='" + yesDayPic + '\'' +
                    '}';
        }

        /**
         * closePri : 299.99
         * highPic : 301.45
         * limit : -0.14%
         * lowPic : 299.60
         * name : 沪金9999
         * openPri : 301.45
         * time : 2019-06-13 21:37:24
         * totalTurnover : 570638000.00
         * totalVol : 1902.00
         * variety : AU99.99
         * yesDayPic : 300.41
         */

        public String closePri;
        public String highPic;
        public String limit;
        public String lowPic;
        public String name;
        public String openPri;
        public String time;
        public String totalTurnover;
        public String totalVol;
        public String variety;
        public String yesDayPic;

        public String getClosePri() {
            return closePri;
        }

        public void setClosePri(String closePri) {
            this.closePri = closePri;
        }

        public String getHighPic() {
            return highPic;
        }

        public void setHighPic(String highPic) {
            this.highPic = highPic;
        }

        public String getLimit() {
            return limit;
        }

        public void setLimit(String limit) {
            this.limit = limit;
        }

        public String getLowPic() {
            return lowPic;
        }

        public void setLowPic(String lowPic) {
            this.lowPic = lowPic;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOpenPri() {
            return openPri;
        }

        public void setOpenPri(String openPri) {
            this.openPri = openPri;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTotalTurnover() {
            return totalTurnover;
        }

        public void setTotalTurnover(String totalTurnover) {
            this.totalTurnover = totalTurnover;
        }

        public String getTotalVol() {
            return totalVol;
        }

        public void setTotalVol(String totalVol) {
            this.totalVol = totalVol;
        }

        public String getVariety() {
            return variety;
        }

        public void setVariety(String variety) {
            this.variety = variety;
        }

        public String getYesDayPic() {
            return yesDayPic;
        }

        public void setYesDayPic(String yesDayPic) {
            this.yesDayPic = yesDayPic;
        }
    }
}
