package org.dop.repository;

import org.dop.entity.Language;
import org.dop.entity.state.LanguageCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, LanguageCode> {
}
