package ro.ubbcluj.apm.am.service.action;

import lombok.RequiredArgsConstructor;
import ro.ubbcluj.apm.am.domain.Identifiable;
import ro.ubbcluj.apm.am.repository.Repository;

import java.util.List;

@RequiredArgsConstructor
public class MultipleDeleteAction<V extends Identifiable<K>, K> implements Action {
    private final Repository<V, K> repository;
    private final List<V> deletedEntities;

    @Override
    public void executeUndo() {
        for (V deletedEntity : deletedEntities) {
            repository.add(deletedEntity);
        }
    }

    @Override
    public void executeRedo() {
        for (V deletedEntity : deletedEntities) {
            repository.deleteById(deletedEntity.getId());
        }
    }
}
