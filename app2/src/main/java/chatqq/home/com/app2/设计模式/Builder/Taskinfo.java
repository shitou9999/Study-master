package chatqq.home.com.app2.设计模式.Builder;

/**
 * 其实就是在实体类中，提供一个静态类，里面再添加共有方法即可
 */

public class Taskinfo {
    private String name; //文件名
    private String url;  //文件url
    private int threadcount; //文需要的线程

    public Taskinfo(Builder builder) {
        this.name=builder.name;
        this.url=builder.url;
        this.threadcount=builder.threadcount;
    }


    public static class Builder{
        private String name;
        private String url;
        private int threadcount;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setThreadcount(int threadcount) {
            this.threadcount = threadcount;
            return this;
        }


        public Taskinfo builder(){
            return new Taskinfo(this);
        }

    }


    @Override
    public String toString() {
        return "Taskinfo{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", threadcount=" + threadcount +
                '}';
    }


}
























