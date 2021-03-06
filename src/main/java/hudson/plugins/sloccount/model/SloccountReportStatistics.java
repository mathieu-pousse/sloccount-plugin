package hudson.plugins.sloccount.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Report statistics. The class is thread safe.
 * 
 * @author Michal Turek
 */
public class SloccountReportStatistics implements Serializable {
    /** Serial version UID. */
    private static final long serialVersionUID = 0L;

    /** The statistics per language. */
    private List<SloccountLanguageStatistics> statistics;

    /**
     * Constructor.
     * 
     * @param statistics the statistics per language
     */
    public SloccountReportStatistics(List<SloccountLanguageStatistics> statistics) {
    	this.statistics = new ArrayList<SloccountLanguageStatistics>(statistics);
    }

    /**
     * Get the statistics.
     * 
     * @return the statistics per language
     */
    public List<SloccountLanguageStatistics> getStatistics() {
        return Collections.unmodifiableList(statistics);
    }

    /**
     * Get total lines count.
     * 
     * @return the lines count
     */
    public int getLineCount() {
        int lineCount = 0;

        for(SloccountLanguageStatistics it : statistics) {
            lineCount += it.getLineCount();
        }

        return lineCount;
    }

    /**
     * Get total files count.
     * 
     * @return the files count
     */
    public int getFileCount() {
        int fileCount = 0;

        for(SloccountLanguageStatistics it : statistics) {
            fileCount += it.getFileCount();
        }

        return fileCount;
    }

    /**
     * Get total languages count.
     * 
     * @return the languages count
     */
    public int getLanguageCount() {
        return statistics.size();
    }

    /**
     * Get language statistics for a concrete language.
     * 
     * @param name the language name
     * @return the statistics
     */
    public SloccountLanguageStatistics getLanguage(String name) {
        for(SloccountLanguageStatistics it : statistics) {
            if(it.getName().equals(name)) {
                return it;
            }
        }

        return new SloccountLanguageStatistics(name, 0, 0);
    }

    /**
     * Get names of all languages.
     * 
     * @return the names
     */
    public List<String> getAllLanguages() {
        List<String> languages = new LinkedList<String>();

        for(SloccountLanguageStatistics it : statistics) {
            languages.add(it.getName());
        }

        return languages;
    }
}
