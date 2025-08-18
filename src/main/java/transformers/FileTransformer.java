package transformers;

import vo.ResultVO;

public interface FileTransformer {
    public ResultVO transform(String... filePaths);
}
