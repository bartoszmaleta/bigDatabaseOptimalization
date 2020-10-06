package com.company.loader;

import java.io.IOException;
import java.util.List;

public interface Loader {

    void setPath(String path);

    List<String[]> load() throws IOException;
}
