package com.web.demo.async;

import com.web.demo.download.DownloadGitHubFiles;
import com.web.demo.dtos.CountriesDTO;
import com.opencsv.bean.CsvToBeanBuilder;
import com.web.demo.utils.ApplicationConstants;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.function.Supplier;

public class ReadCountriesSupplier implements Supplier<List<CountriesDTO>> {
    @Override
    public List<CountriesDTO> get() {
        try {
            DownloadGitHubFiles.downloadFile("csv/CountriesRegions.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<CountriesDTO> listCrop = null;
        try {
            listCrop = new CsvToBeanBuilder(new FileReader(ApplicationConstants.DOWNLOAD_LOCATION + "csv/CountriesRegions.csv"))
                    .withType(CountriesDTO.class)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return listCrop;
    }
}
