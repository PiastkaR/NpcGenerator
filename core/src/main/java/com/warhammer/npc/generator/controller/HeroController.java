package com.warhammer.npc.generator.controller;

import com.lowagie.text.DocumentException;
import com.warhammer.npc.generator.hero.Hero;
import com.warhammer.npc.generator.hero.HeroDescription;
import com.warhammer.npc.generator.hero.abilities.Ability;
import com.warhammer.npc.generator.hero.characteristics.MainCharacteristics;
import com.warhammer.npc.generator.hero.characteristics.SecondaryCharacteristics;
import com.warhammer.npc.generator.hero.skills.Skill;
import com.warhammer.npc.generator.model.Equipment;
import com.warhammer.npc.generator.service.CharacterCreatorService;
import com.warhammer.npc.generator.service.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

//@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/heros")
public class HeroController {

    private final CharacterCreatorService creatorService;
    private final HeroService heroService;

    @GetMapping(path = "/heroDesc")
    @ResponseBody
    public HeroDescription generateHeroDescription(@RequestParam String race, @RequestParam String gender, @RequestParam String charactersClass) {
        return creatorService.getHeroDescription(race, gender, charactersClass);
    }

    @GetMapping(path = "/hero")
    @ResponseBody
    public Hero generateHero(@RequestParam String race, @RequestParam String gender, @RequestParam String charactersClass) {
        return creatorService.getHero(race, gender, charactersClass);
    }

    @GetMapping(path = "/card")
    public String getCard(@RequestParam String race, @RequestParam String gender, @RequestParam String charactersClass,
                          @RequestParam String userName, Model model) throws IOException, DocumentException {
        Hero hero = creatorService.getHero(race, gender, charactersClass);

        heroService.resolveDescriptionModel(model, userName, race, gender, hero);
        heroService.resolveMainCharacteristics(model, hero);
        heroService.resolveSecondaryCharacteristics(model, hero);
        heroService.resolveSpeed(hero.getSecondaryCharacteristics().getSpeed(), model);

        MainCharacteristics mainFromProfession = hero.getDescription().getActualProfession().getMainCharacteristicsDevelopment();
        SecondaryCharacteristics secondaryFromProfession = hero.getDescription().getActualProfession().getSecondaryCharacteristicsDevelopment();
        heroService.resolveMainCharacteristicsFromProfession(model, mainFromProfession);
        heroService.resolveSecondaryCharacteristicsFromProfession(model, secondaryFromProfession);

        String actualProfession = hero.getDescription().getActualProfession().getDescription();
        model.addAttribute("actualProfession", actualProfession);

        List<Equipment> equipment = hero.getDescription().getActualProfession().getEquipment();
        heroService.assignEquipment(equipment, model);

        List<Skill> skills = hero.getDescription().getActualProfession().getSkills();
        heroService.assignSkillNames(skills, model);

        List<Ability> abilities = hero.getDescription().getActualProfession().getAbilities();
        heroService.assignAbilitiesNames(abilities, model);
        heroService.getMoney(model);

//        File inputHTML = new File("resources/templates/karta_z_tekstem.html");
//        Document document = Jsoup.parse(inputHTML, "UTF-8");
//        Document.OutputSettings xhtml = document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
//
//        try (OutputStream outputStream = new FileOutputStream("outputPdf")) {
//            ITextRenderer renderer = new ITextRenderer();
//            SharedContext sharedContext = renderer.getSharedContext();
//            sharedContext.setPrint(true);
//            sharedContext.setInteractive(false);
//            renderer.setDocumentFromString(String.valueOf(xhtml));
//            renderer.layout();
//            renderer.createPDF(outputStream);
//        }

        return "karta_z_tekstem";
    }

//    public void generatePdfFromHtml(String html) throws IOException, com.lowagie.text.DocumentException {
//        String outputFolder = System.getProperty("user.home") + File.separator + "thymeleaf.pdf";
//        OutputStream outputStream = new FileOutputStream(outputFolder);
//
//        ITextRenderer renderer = new ITextRenderer();
//        renderer.setDocumentFromString(html);
//        renderer.layout();
//        renderer.createPDF(outputStream);
//
//        outputStream.close();
//    }
//
//    private void parseThymeleafTemplate() throws ParserConfigurationException, IOException, DocumentException, SAXException {
//        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//        templateResolver.setSuffix(".html");
//        templateResolver.setPrefix("/templates/");
//        templateResolver.setTemplateMode(TemplateMode.HTML);
//
//        TemplateEngine templateEngine = new TemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver);
//
//        Context context = new Context();
//        context.setVariable("to", "Baeldung");
////        templateEngine.process("karta_z_tekstem", context);
//
//        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//        Document doc = builder.parse(new StringBufferInputStream(templateEngine.process("karta_z_tekstem", context)));
//
//        ITextRenderer renderer = new ITextRenderer();
//        renderer.setDocument(doc, null);
//
//        String outputFile = "100bottles.pdf";
//        OutputStream os = new FileOutputStream(outputFile);
//        renderer.layout();
//        renderer.createPDF(os);
//        os.close();


//        return templateEngine.process("karta_z_tekstem", context);
//    }

//    @GetMapping(path = "/pdf")
//    public void getPdf() throws IOException {
//
//        return document;

//        parseThymeleafTemplate();
//        generatePdfFromHtml(parseThymeleafTemplate);
//    }


}
