package be.seeseemelk.mockbukkit.attribute;

import com.google.common.base.Preconditions;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AttributeInstanceMock implements AttributeInstance
{

	private final @NotNull Attribute attribute;
	private final double defaultValue;
	private double value;
	private final @NotNull List<AttributeModifier> modifiers;

	public AttributeInstanceMock(@NotNull Attribute attribute, double value)
	{
		Preconditions.checkNotNull(attribute, "Attribute cannot be null");
		this.attribute = attribute;
		this.defaultValue = value;
		this.value = value;
		modifiers = new ArrayList<>();
	}

	@Override
	public @NotNull Attribute getAttribute()
	{
		return attribute;
	}

	@Override
	public double getBaseValue()
	{
		return value;
	}

	@Override
	public void setBaseValue(double value)
	{
		this.value = value;
	}

	@Override
	public @NotNull Collection<AttributeModifier> getModifiers()
	{
		return modifiers;
	}

	@Override
	public void addModifier(@NotNull AttributeModifier modifier)
	{
		Preconditions.checkNotNull(modifier, "Modifier cannot be null");
		modifiers.add(modifier);
	}

	@Override
	public void removeModifier(@NotNull AttributeModifier modifier)
	{
		Preconditions.checkNotNull(modifier, "Modifier cannot be null");
		modifiers.remove(modifier);
	}

	@Override
	public double getValue()
	{
		return getBaseValue();
	}

	@Override
	public double getDefaultValue()
	{
		return defaultValue;
	}

}
