package tk.zater.CS;

import java.io.Serializable;
import java.util.Date;

public class FileName implements Serializable, Comparable<FileName> {

    @Override 
    public String toString() {
        return new Date().toLocaleString()+ "{" + "filename=" + filename +", size=" + size + '}';
    }

    public String filename;
    public String version;
    public String versionid;
    public String build;
    public String Url;
    public int size;

    @Override
    public int hashCode() {
        int result=0;
        for(int i=0;i<filename.length();i++){
        result+=(int)filename.charAt(i);
        
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        FileName other = (FileName) obj;
        if (filename == null) {
            if (other.filename != null) {
                return false;
            }
        } else if (filename.equals(other.filename)) {
            return true;
        }
        return false;
    }

    public boolean checkthesame(FileName o) {
        if(o.filename==null||o.Url==null||o.build==null||o.versionid==null||o.version==null)
            return true;
        if (this.version.equals(o.version) && this.versionid.equals(o.versionid) && this.build.equals(o.build) && this.size == o.size) {
            return true;
        }
        return false;
    }

    public int compareTo(FileName o) {
        if (this.filename.equals(o.filename)) {
            return 0;
        } else {
            return o.filename.compareTo(this.filename);
        }
    }

}
