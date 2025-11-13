package ro.ubbcluj.apm.am.service.action;

import lombok.RequiredArgsConstructor;
import ro.ubbcluj.apm.am.domain.Identifiable;
import ro.ubbcluj.apm.am.repository.Repository;

@RequiredArgsConstructor
public class DeleteAction<V extends Identifiable<K>, K> implements Action {
    private final Repository<V, K> repository;
    private final V deletedEntity;

    @Override
    public void executeUndo() {
        repository.add(deletedEntity);
    }

    @Override
    public void executeRedo() {
        repository.deleteById(deletedEntity.getId());
    }
}
