package com.gokhantamkoc.javabootcamp.odevhafta2;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlayWorldOfMagic implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(PlayWorldOfMagic.class, args);
	}
	/*
	 * Göreviniz büyücümüzün minimum sayıda büyü kullanarak bölüm sonu canavarlarını
	 * yenip yenemeyeceğini anlayan bir algoritma yazmanız.
	 */

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		int maxNumOfAttacksAllowed = 9;
		String[] magicianSpells = createSpellNameRepository();
		float[] spellDamageInfo = createSpellDamageRepository();
		String[] bossNames = createBossNameRepository();
		float[] bossHps = createBossHPRepository();

		int minNumberSpellsUsed = resolveBattle(magicianSpells, spellDamageInfo, bossNames, bossHps);

		if (minNumberSpellsUsed > maxNumOfAttacksAllowed) {
			System.out.println("Magician died!");
		} else if (minNumberSpellsUsed > 0 && minNumberSpellsUsed <= maxNumOfAttacksAllowed) {
			System.out.println("Magician won the battle!");
		} else {
			System.out.println("Result is not correct!");
		}
	}

	public static int resolveBattle(String[] magicianSpells, float[] spellDamageInfo, String[] bossNames,
			float[] bossHPs) {

		int spellsUsed = 0;
		// ______ BASLANGIC _______ Kodunuz buradan baslamali
		float mostEffectiveMagicPower = 0;
		for (int i = 0; i < spellDamageInfo.length; i++) {
			if (spellDamageInfo[i] > mostEffectiveMagicPower) {
				mostEffectiveMagicPower = spellDamageInfo[i]; // en etkili büyüyü bulduk. Bu sayede daha az büyü kullanacağız.

			}
		}

		int indexOfMostEffectiveMagicPower = 0;
		for (int i = 0; i < spellDamageInfo.length; i++) {
			if (spellDamageInfo[i] == mostEffectiveMagicPower) {
				mostEffectiveMagicPower = i; // en etkili büyünün dizi içerisinde ki indeksini bulduk.
			}
		}

		String mostEffectiveMagicName = magicianSpells[indexOfMostEffectiveMagicPower]; // en etkili büyünün ismini bulduk. Ama büyülerin isimleri üzerinden değil, etkileri üzerinden yapacağımız için büyü ismi gereksiz oluyor biraz.

		for (int i = 0; i < bossHPs.length; i++) {
			bossHPs[i] -= mostEffectiveMagicPower; // en etkili büyü birinci canavara vuruyor.

			if (bossHPs[i] <= 0) { // canavarın canı sıfır altına inerse canavar öldü ve sonra ki canavara geçeceğiz.

				i++;
				spellsUsed++;
				continue;
			}
			spellsUsed++;
		}

		// ______ SON _______ Kodunuz burada bitmeli
		/*
		 * NOT: ______ BASLANGIC _______ ve ______ SON _______ arasina istediginiz kadar
		 * sayida satir ekleyebilirsiniz.
		 */
		return spellsUsed;
	}

	public static String[] createSpellNameRepository() { // büyü çeşitleri
		return new String[] { "Ice Storm", "Chain Lightning", "Magic Missile" };
	}

	public static float[] createSpellDamageRepository() { // yapılan büyüye göre hasar miktarları
		return new float[] { 40.0f, 30.0f, 5.0f };
	}

	public static String[] createBossNameRepository() { // canavar çeşitleri
		return new String[] { "Dire Rat", "Skeleton Knight", "Undead King" };
	}

	public static float[] createBossHPRepository() { // canavar hasarları
		return new float[] { 15.0f, 45.0f, 60.0f };
	}
}
