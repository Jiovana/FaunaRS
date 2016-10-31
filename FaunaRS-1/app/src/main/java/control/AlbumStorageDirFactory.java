package control;

import java.io.File;

/**
 * Created by gomes on 30/10/2016.
 */
abstract class AlbumStorageDirFactory {
    public abstract File getAlbumStorageDir(String albumName);
}