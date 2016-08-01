package pl.spring.demo.common;

import pl.spring.demo.to.IdAware;

import java.util.Collection;

/**
 * Responsible for maintenance of sequenced numbering of id-aware data.
 * 
 * @author Pawel Patronik
 *
 */
public class Sequence {

    /**
     * Generates next value of id based on given collection of id-containing data.
     * 
     * @param existingIds
     * @return
     */
    public long nextValue(Collection<? extends IdAware> existingIds) {
        long result = 1;
        for (IdAware nextExistingId : existingIds) {
            if (Long.compare(nextExistingId.getId(), result) > 0) {
                result = nextExistingId.getId();
            }
        }
        return result;
    }
}
