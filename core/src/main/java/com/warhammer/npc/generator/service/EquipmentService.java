package com.warhammer.npc.generator.service;

import com.warhammer.npc.generator.hero.repository.ArmourRepository;
import com.warhammer.npc.generator.hero.repository.WeaponRepository;
import com.warhammer.npc.generator.mechanics.MechanicsUtils;
import com.warhammer.npc.generator.model.Armour;
import com.warhammer.npc.generator.model.Equipment;
import com.warhammer.npc.generator.model.Profession;
import com.warhammer.npc.generator.model.Weapon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class EquipmentService {

    private final WeaponRepository weaponRepository;
    private final ArmourRepository armourRepository;

    public List<Equipment> getEquipment(Profession profession) {
        List<String> professionEquipment = profession.getEquipment();
        List<Equipment> equipment = new ArrayList<>();

        List<Equipment> basics = getBasics();
        equipment.addAll(basics);
        List<Equipment> armour = searchForArmour(professionEquipment);
        equipment.addAll(armour);
        List<Equipment> weapons = searchForWeapons(professionEquipment);
        equipment.addAll(weapons);

        return equipment;
    }

    private List<Equipment> searchForArmour(List<String> professionEquipment) {
        Iterable<Armour> all = armourRepository.findAll();
        List<Equipment> armours = StreamSupport.stream(all.spliterator(), false).collect(Collectors.toList());
        List<String> armoursNames = armours.stream().map(Equipment::getName).collect(Collectors.toList());

        if (professionEquipment == null) {
            return Collections.emptyList();
        }

        List<String> filteredArmoursFromProfession = professionEquipment.stream().filter(equip -> armoursNames.contains(equip)).collect(Collectors.toList());

        return getEquipment(filteredArmoursFromProfession, armours);
    }

    private List<Equipment> getEquipment(List<String> equipAsString, List<Equipment> equip) {
        List<Equipment> result = new ArrayList<>();
        equipAsString.forEach(equip1 -> {
            for (Equipment equip2 : equip) {
                if (equip2.getName().equals(equip1)) {
                    result.add(equip2);
                }
            }
        });

        return result;
    }

    private List<Equipment> searchForWeapons(List<String> professionEquipment) {
        Iterable<Weapon> all = weaponRepository.findAll();
        List<Equipment> weapons = StreamSupport.stream(all.spliterator(), false).collect(Collectors.toList());
        List<String> weaponNames = weapons.stream().map(Equipment::getName).collect(Collectors.toList());

        if (professionEquipment == null) {
            return Collections.emptyList();
        }

        List<String> filteredWeaponsFromProfession = professionEquipment.stream().filter(equip -> weaponNames.contains(equip)).collect(Collectors.toList());

        return getEquipment(filteredWeaponsFromProfession, weapons);
    }

    private List<Equipment> getBasics() {
        List<Equipment> equipment = new ArrayList<>();
        Weapon sword = new Weapon(103L, "Miecz jednoręczny", 50, "zwykła", "S", "-", "-", Collections.EMPTY_LIST);
        equipment.add(sword);
        Equipment eq1 = new Equipment(1L, "Pochodnia", 5);
        equipment.add(eq1);
        Equipment eq2 = new Equipment(2L, "Hubka i krzesiwo", 0);
        equipment.add(eq2);
        Equipment eq3 = new Equipment(3L, "śpiwór", 15);
        equipment.add(eq3);

        return equipment;
    }

    public int generateGold() {
        return MechanicsUtils.getRandomNumberUsingNextInt(1, 10);
    }

}
