package com.cyberware.conversation;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import java.util.*;

public class ConversationManager {
    
    private static final Map<String, Conversation> CONVERSATIONS = new HashMap<>();
    
    static {
        // Lade alle Conversations
        registerConversations();
    }
    
    public static void startConversation(Player player, String conversationId) {
        Conversation conv = CONVERSATIONS.get(conversationId);
        if(conv != null) {
            conv.start(player);
        }
    }
    
    private static void registerConversations() {
        // LUCY CONVERSATION
        Conversation lucy = new Conversation("lucy_default");
        lucy.addDialogue(0, new DialogueNode("lucy", "Hey choom, was brauchst du?",
                new String[]{"Erzähl mir von deiner Zeit", "Ich brauche Cyberware", "Auf Wiedersehen"},
                new int[]{1, 2, -1}));
        lucy.addDialogue(1, new DialogueNode("lucy", "Ich war Netrunner... bis alles schiefging.",
                new String[]{"Bedauerlich", "Was ist passiert?", "Zurück"},
                new int[]{-1, 3, 0}));
        lucy.addDialogue(3, new DialogueNode("lucy", "Das ist eine lange Geschichte, V...",
                new String[]{"Verstanden", "Zurück"},
                new int[]{-1, 0}));
        lucy.addDialogue(2, new DialogueNode("lucy", "Ich hab für dich was Gutes...",
                new String[]{"Danke!", "Zurück"},
                new int[]{-1, 0}));
        CONVERSATIONS.put("lucy_default", lucy);
        
        // DAVID MARTINEZ CONVERSATION
        Conversation david = new Conversation("david_martinez");
        david.addDialogue(0, new DialogueNode("david", "Ich werde nicht verlieren! Meine Chrome ist stärker!",
                new String[]{"Du schaffst es, David", "Das ist verrückt", "Komm zu Sinnen!"},
                new int[]{1, 2, 3}));
        david.addDialogue(1, new DialogueNode("david", "Danke, V... vielleicht... hast du recht...",
                new String[]{"Du packst das", "Vorsicht vor Überlas"},
                new int[]{-1, 4}));
        david.addDialogue(2, new DialogueNode("david", "VERRÜCKT?! Das ist STÄRKE!",
                new String[]{"Entschuldigung", "Zurück"},
                new int[]{3, 0}));
        david.addDialogue(3, new DialogueNode("david", "...Vielleicht hast du recht... Die Chrome... Sie konsumiert mich...",
                new String[]{"Es gibt Hilfe", "Zu viel Cyberware?", "Tschüss"},
                new int[]{5, 4, -1}));
        david.addDialogue(4, new DialogueNode("david", "Chrome-Psychose... Ja, ich kenne das Risiko...",
                new String[]{"Hör auf damit", "Ripperdoc aufsuchen"},
                new int[]{-1, 5}));
        david.addDialogue(5, new DialogueNode("david", "Vielleicht... vielleicht sollte ich weniger Chrome nutzen...",
                new String[]{"Weise Entscheidung", "Zurück"},
                new int[]{-1, 0}));
        CONVERSATIONS.put("david_martinez", david);
        
        // ADAM SMASHER CONVERSATION (Threatening)
        Conversation smasher = new Conversation("adam_smasher");
        smasher.addDialogue(0, new DialogueNode("smasher", "Huh? Ein anderer Cyborg? Interessant...",
                new String[]{"Ich suche Kampf", "Ich respektiere dich", "Lass mich in Ruhe"},
                new int[]{1, 2, 3}));
        smasher.addDialogue(1, new DialogueNode("smasher", "Du willst gegen ADAM SMASHER kämpfen? HAHA! Dumm!",
                new String[]{"Ich bin stark genug", "Entschuldigung", "Zurück"},
                new int[]{4, 5, 0}));
        smasher.addDialogue(4, new DialogueNode("smasher", "LMAO! Deine Chrome ist MÜLL! Lass mich dir zeigen wie es geht!",
                new String[]{"KAMPF!", "Warte!"},
                new int[]{6, 3}));
        smasher.addDialogue(6, new DialogueNode("smasher", "TO THE PAIN!",
                new String[]{}));
        smasher.addDialogue(2, new DialogueNode("smasher", "Respekt? Ha! Das brauche ich nicht. Aber... ich respektiere auch dich.",
                new String[]{"Danke", "Du bist Legende", "Zurück"},
                new int[]{-1, 7, 0}));
        smasher.addDialogue(7, new DialogueNode("smasher", "Verdammt richtig! Ich bin die BESTE Waffe die es gibt!",
                new String[]{"Unbestritten", "Zurück"},
                new int[]{-1, 0}));
        smasher.addDialogue(3, new DialogueNode("smasher", "Weise Wahl.",
                new String[]{}));
        smasher.addDialogue(5, new DialogueNode("smasher", "Vergessen wir es. Nächstes Mal.",
                new String[]{}));
        CONVERSATIONS.put("adam_smasher", smasher);
        
        // MaxTac Police Cyborg
        Conversation maxtac = new Conversation("maxtac_cyborg");
        maxtac.addDialogue(0, new DialogueNode("maxtac", "MaxTac Einsatzteam! Dokumentation erforderlich!",
                new String[]{"Ich bin sauber", "Cyberware-Check?", "Was ist das hier?"},
                new int[]{1, 2, 3}));
        maxtac.addDialogue(1, new DialogueNode("maxtac", "Durchlasspunkte überprüft... Sauber. Pass auf dich auf.",
                new String[]{"Danke", "Cyberkriminalität?", "Zurück"},
                new int[]{-1, 4, 0}));
        maxtac.addDialogue(2, new DialogueNode("maxtac", "Standardisiertes Scan-Protokoll. Zeige deine Cyberware!",
                new String[]{"Hier, schau", "Weigere ich mich", "Zurück"},
                new int[]{5, 6, 0}));
        maxtac.addDialogue(3, new DialogueNode("maxtac", "MaxTac - Militärische Unterdrückung und Verfolgung. Wir sind das Beste was NC hat.",
                new String[]{"Gefährlich", "Respekt", "Zurück"},
                new int[]{-1, 7, 0}));
        maxtac.addDialogue(4, new DialogueNode("maxtac", "Cyberkriminelle nutzen illegale Schwarzmarkt-Cyberware. Halte deine sauber.",
                new String[]{"Verstanden", "Zurück"},
                new int[]{-1, 0}));
        maxtac.addDialogue(5, new DialogueNode("maxtac", "...Hm. Nicht auf der Blacklist. Freigegeben. Geh weiter.",
                new String[]{"Danke", "Zurück"},
                new int[]{-1, 0}));
        maxtac.addDialogue(6, new DialogueNode("maxtac", "GROBES VERHALTEN GEGENÜBER DEN BEHÖRDEN! Verhaftung eingeleitet!",
                new String[]{"Nein warte!"}));
        maxtac.addDialogue(7, new DialogueNode("maxtac", "Ja. Aber auch teuer für NC. Nur für Probleme... wie du.",
                new String[]{"Stimme zu", "Zurück"},
                new int[]{-1, 0}));
        CONVERSATIONS.put("maxtac_cyborg", maxtac);
        
        // Ripperdoc Conversation
        Conversation ripperdoc = new Conversation("ripperdoc_installation");
        ripperdoc.addDialogue(0, new DialogueNode("ripperdoc", "Willkommen, Choom! Brauchst du neue Chrome?",
                new String[]{"Cyberware Vorschlag", "Slot überprüfen", "Preise?", "Tschüss"},
                new int[]{1, 2, 3, -1}));
        ripperdoc.addDialogue(1, new DialogueNode("ripperdoc", "Basierend auf deinem Stil würde ich sagen... Sandevistan + Ping. Schnell und Agil!",
                new String[]{"Klingt gut", "Andere Optionen", "Zurück"},
                new int[]{4, 5, 0}));
        ripperdoc.addDialogue(2, new DialogueNode("ripperdoc", "Lass mich schauen... Du hast noch Platz! Welche Kategorie interessiert dich?",
                new String[]{"Kampf", "Hacking", "Verteidigung", "Sinne"},
                new int[]{6, 7, 8, 9}));
        ripperdoc.addDialogue(3, new DialogueNode("ripperdoc", "Kommt drauf an... Standard 100-500 Eddies. Exotisch? 5000+. Legendär? Frag nicht.",
                new String[]{"Verstanden", "Zurück"},
                new int[]{-1, 0}));
        ripperdoc.addDialogue(4, new DialogueNode("ripperdoc", "Gut gewählt! Lass mich die installieren...",
                new String[]{}));
        ripperdoc.addDialogue(5, new DialogueNode("ripperdoc", "Versuch Berserk für Nahkampf oder Quickhacking für Hacking!",
                new String[]{"Interessant", "Zurück"},
                new int[]{-1, 1}));
        ripperdoc.addDialogue(6, new DialogueNode("ripperdoc", "Kampf-Modi: Berserk, Mantis Blades, Gorilla Arms. Was willst du?",
                new String[]{}));
        ripperdoc.addDialogue(7, new DialogueNode("ripperdoc", "Hacking: Quickhacking, Ping, Breach Protocol. Für echte Netrunner!",
                new String[]{}));
        ripperdoc.addDialogue(8, new DialogueNode("ripperdoc", "Defensive: Chrome Body, Dermal Armor, Regeneration. Überleben!",
                new String[]{}));
        ripperdoc.addDialogue(9, new DialogueNode("ripperdoc", "Sinne: Thermal Vision, Zoom Optics, Scanner. Wissen ist Macht!",
                new String[]{}));
        CONVERSATIONS.put("ripperdoc_installation", ripperdoc);
    }
    
    public static class DialogueNode {
        public String npcName;
        public String text;
        public String[] choices;
        public int[] nextNodeIds;
        
        public DialogueNode(String npcName, String text, String[] choices, int... nextNodeIds) {
            this.npcName = npcName;
            this.text = text;
            this.choices = choices;
            this.nextNodeIds = nextNodeIds;
        }
        
        public DialogueNode(String npcName, String text, String[] choices) {
            this(npcName, text, choices);
        }
    }
    
    public static class Conversation {
        private String id;
        private Map<Integer, DialogueNode> nodes = new HashMap<>();
        private int currentNode = 0;
        
        public Conversation(String id) {
            this.id = id;
        }
        
        public void addDialogue(int nodeId, DialogueNode node) {
            nodes.put(nodeId, node);
        }
        
        public void start(Player player) {
            currentNode = 0;
            displayNode(player);
        }
        
        public void selectChoice(Player player, int choiceIndex) {
            DialogueNode current = nodes.get(currentNode);
            if(choiceIndex < current.nextNodeIds.length) {
                int nextId = current.nextNodeIds[choiceIndex];
                if(nextId == -1) {
                    // Conversation endet
                    player.displayClientMessage(Component.literal("§8[Conversation beendet]"), false);
                } else {
                    currentNode = nextId;
                    displayNode(player);
                }
            }
        }
        
        private void displayNode(Player player) {
            DialogueNode node = nodes.get(currentNode);
            player.displayClientMessage(
                Component.literal("§e[" + node.npcName + "] §f" + node.text),
                false
            );
            
            for(int i = 0; i < node.choices.length; i++) {
                player.displayClientMessage(
                    Component.literal("§7" + (i+1) + ". " + node.choices[i]),
                    false
                );
            }
        }
        
        public int getCurrentNode() {
            return currentNode;
        }
    }
}
