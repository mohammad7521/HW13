package services;

import entities.Term;
import repository.TermRepo;

import java.util.List;

public class TermRepoServices implements BaseServices<Term>{

    private static TermRepo termRepo=new TermRepo();

    @Override
    public Term add(Term term) {
        return termRepo.add(term);
    }

    @Override
    public Term remove(int id) {

        Term returnedTerm=termRepo.showInfo(id,Term.class);
        return termRepo.remove(returnedTerm);
    }

    @Override
    public void update(Term term) {
        termRepo.update(term);
    }

    @Override
    public Term showInfo(int id) {
        return termRepo.showInfo(id,Term.class);
    }

    @Override
    public List<Term> showAll() {
        return termRepo.showAll(Term.class);
    }

}
