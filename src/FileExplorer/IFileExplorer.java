package FileExplorer;

/**
 * Created by hanu on 11/16/16.
 */
public interface IFileExplorer {
    String folderPath = null;
    void SetPath(String folderPath);
    String GetPath();
    String fileName = null;
    void SetFileName(String fileName);
    String GetFileName();

}
