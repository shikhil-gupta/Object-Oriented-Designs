public enum ChessPieceTypes {
    PAWN("pawn");

    private String type;

    ChessPieceTypes(String type) {
        this.type=type;
    }

    public String getType() {
        return type;
    }
}
