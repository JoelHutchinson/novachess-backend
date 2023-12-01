package com.novachess;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface OpeningVariationRepository extends JpaRepository<OpeningVariation, Long> {

}
