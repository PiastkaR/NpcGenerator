package com.warhammer.npc.generator.service;

import com.warhammer.npc.generator.hero.abilities.Ability;
import com.warhammer.npc.generator.hero.description.CharactersClass;
import com.warhammer.npc.generator.hero.description.Race;
import com.warhammer.npc.generator.hero.repository.AbilityRepository;
import com.warhammer.npc.generator.hero.repository.ProfessionRepository;
import com.warhammer.npc.generator.hero.repository.SkillRepository;
import com.warhammer.npc.generator.hero.skills.Skill;
import com.warhammer.npc.generator.mechanics.MechanicsUtils;
import com.warhammer.npc.generator.model.Equipment;
import com.warhammer.npc.generator.model.Profession;
import com.warhammer.npc.generator.model.ProfessionWrapperBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProfessionService {

    private final ProfessionRepository professionRepository;
    private final AbilityRepository abilityRepository;
    private final SkillRepository skillRepository;
    private final EquipmentService equipmentService;

    public ProfessionWrapperBuilder getProfession(Race race, CharactersClass charactersClass) {
        Profession profession = generateProfessionValues(race, charactersClass);
        List<Ability> abilities = mapAbilitiesForProfession(profession.getAbilities());
        List<Skill> skills = mapSkillsForProfession(profession.getSkills());

        return new ProfessionWrapperBuilder()
                .withId(profession.getId())
                .withDescription(profession.getDescription())
                .withCharactersClass(charactersClass)
                .withRace(profession.getRacesProfession())
                .withMainCharacteristics(profession.getMainCharacteristicsDevelopment())
                .withSecondaryCharacteristics(profession.getSecondaryCharacteristicsDevelopment())
                .withEquipment(getEquipment(profession))
                .withSkills(skills)
                .withAbilities(abilities)
                .buildWithSkillsAndAbilities();

    }

    private List<Equipment> getEquipment(Profession profession) {
        return equipmentService.getEquipment(profession);
    }

    private Profession generateProfessionValues(Race race, CharactersClass charactersClass) {
        Iterable<Profession> all = professionRepository.findAll();
        List<Profession> professions = new ArrayList<>();
        List<Profession> collect = StreamSupport.stream(all.spliterator(), false)
                .collect(Collectors.toList());
        professions.addAll(collect);

        List<Profession> filteredProfessions = MechanicsUtils.filterData(professions, profession -> profession.getRacesProfession().contains(race),
                profession -> profession.getCharactersClass() == charactersClass);
        int draw = MechanicsUtils.drawFromFiltered(filteredProfessions);

        return filteredProfessions.get(draw);
    }

    private List<Ability> mapAbilitiesForProfession(List<String> abilitiesAsString) {
        Iterable<Ability> all = abilityRepository.findAll();
        ArrayList<Ability> abilities = new ArrayList<>();
        List<Ability> immutableAbilities = StreamSupport.stream(all.spliterator(), false).collect(Collectors.toList());
        abilities.addAll(immutableAbilities);

        List<Ability> editableAbilities = getAbilities(abilitiesAsString, abilities);

        return editableAbilities;
    }

    private List<Ability> getAbilities(List<String> abilitiesAsString, List<Ability> abilities) {
        List<Ability> editableAbilities = new ArrayList<>();
        abilitiesAsString.forEach(ability1 -> {
            for (Ability ability2 : abilities) {
                if(ability2.getName().equals(ability1))
                {
                    editableAbilities.add(ability2);
                }
            }
        });

        return editableAbilities;
    }

    private List<Skill> mapSkillsForProfession(List<String> skillsAsString) {
        Iterable<Skill> all = skillRepository.findAll();
        ArrayList<Skill> skills = new ArrayList<>();
        List<Skill> immutableSkills = StreamSupport.stream(all.spliterator(), false).collect(Collectors.toList());
        skills.addAll(immutableSkills);
        List<Skill> editableSkills = getSkills(skillsAsString, skills);

        return editableSkills;
    }

    private List<Skill> getSkills(List<String> abilitiesAsString, List<Skill> skills) {
        List<Skill> editableAbilities = new ArrayList<>();
        abilitiesAsString.forEach(skill1 -> {
            for (Skill skill2 : skills) {
                if(skill2.getName().equals(skill1))
                {
                    editableAbilities.add(skill2);
                }
            }
        });

        return editableAbilities;
    }

}
