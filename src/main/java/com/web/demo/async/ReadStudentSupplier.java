package com.web.demo.async;

import com.web.demo.download.DownloadGitHubFiles;
import com.web.demo.dtos.StudentDTO;
import com.opencsv.bean.CsvToBeanBuilder;
import com.web.demo.utils.ApplicationConstants;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.function.Supplier;

public class ReadStudentSupplier implements Supplier<List<StudentDTO>> {
    @Override
    public List<StudentDTO> get() {
        try {
            DownloadGitHubFiles.downloadFile("csv/StudentInfo.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<StudentDTO> listCrop = null;
        try {
            listCrop = new CsvToBeanBuilder(new FileReader(ApplicationConstants.DOWNLOAD_LOCATION + "csv/StudentInfo.csv"))
                    .withType(StudentDTO.class)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return listCrop;
    }
}
