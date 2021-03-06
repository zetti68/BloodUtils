package bloodutils.main.init;

import java.awt.Color;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import wasliecore.interfaces.IInitalization;
import bloodutils.api.compact.CompactItem;
import bloodutils.api.registries.RevivingRegistry;
import bloodutils.entities.EntityRoyal;
import bloodutils.entities.reviving.RevivingRoyal;
import bloodutils.main.BU;
import cpw.mods.fml.common.registry.EntityRegistry;

public class BUEntities implements IInitalization{
	
	@Override
	public void preInit(){
		EntityRegistry.registerModEntity(EntityRoyal.class, "royal", 55, BU.instance, 128, 3, true);
        registerEntityEgg(EntityRoyal.class, new Color(0, 0, 100).getRGB(), new Color(100, 0, 0).getRGB());
	}

	public static void registerEntityEgg(Class<? extends Entity> entity, int colPrim, int colSec) {
		int id = getUniqueEntityID();
		EntityList.IDtoClassMapping.put(id, entity);
		EntityList.entityEggs.put(id, new EntityList.EntityEggInfo(id, colPrim, colSec));
		return;
	}

	private static int getUniqueEntityID() {
		int startEID = 300;
		do{
			startEID++;
		}while(EntityList.getStringFromID(startEID) != null);
		return startEID;
	}
	@Override
	public void init() {
		
	}

	@Override
	public void postInit() {
		initReviving();
	}
	
	public void initReviving(){
		RevivingRegistry.registerReviving(new CompactItem(BUItems.diamond_blood, BUItems.gem_darkness), new RevivingRoyal());
	}
}