public enum BoxType {

    BLACK("black"), WHITE("white");

    private String boxtype;

    BoxType(String boxtype) {
        this.boxtype=boxtype;
    }

    public String getBoxtype() {
        return boxtype;
    }
}
