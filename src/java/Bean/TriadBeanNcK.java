/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * @author u27brvz18
 */
public class TriadBeanNcK {
    private String[] chunks;
    private String delimiter;
    private int size;

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        if (delimiter == null || delimiter.isEmpty())
            throw new IllegalArgumentException("Разделитель должен быть установлен!");

        this.delimiter = delimiter;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        if (size < 1)
            throw new IllegalArgumentException("Размер группы должен быть положительным числом!");

        this.size = size;
    }

    public void setChunks(String src) {
        if (delimiter == null || size == 0)
            throw new IllegalStateException("Не установлен разделитель или размер группы!");

        int chunksCount = (int) Math.ceil((double) src.length() / (double) size);
        chunks = new String[chunksCount];
        for (int i = 0; i < chunks.length; i++)
            chunks[i] = src.substring(i * size, Math.min(src.length(), (i + 1) * size));
    }

    public String getChunks() {
        if (chunks == null)
            return "";

        return Arrays.stream(chunks)
                 .map(String::trim)
                 .collect(Collectors.joining(delimiter));
        
    }  
}
