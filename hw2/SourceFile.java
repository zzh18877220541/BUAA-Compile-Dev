class SourceFile {
    private String input;
    private int index;
    private int length;

    public SourceFile(String input) {
        this.input = input;
        this.index = 0;
        this.length = input.length();
    }

    public char readNextchar() {
        return input.charAt(index++);
    }

    public boolean isEndOfInput() {
        return index >= length;
    }

}