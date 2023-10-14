
package Repository;

import DataAccess.FileProcessing;


public class FileRepository implements IFileRepository{

    @Override
    public void getPerson() {
      FileProcessing.Instance().findPersonInfo();
    }

    @Override
    public void copyWordOneTimes() {
        FileProcessing.Instance().copyNewFile();
    }
    
}
