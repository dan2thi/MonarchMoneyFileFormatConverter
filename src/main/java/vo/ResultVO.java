package vo;

public class ResultVO {

    private boolean result;
    private String outputfilePath;
    private String message;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getOutputfilePath() { return outputfilePath; }

    public void setOutputfilePath(String outputfilePath) { this.outputfilePath = outputfilePath; }

    public String getMessage() { return message; }

    public void setMessage(String message) {this.message = message; }
}
