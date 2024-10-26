package fox.mods.economy.main;

import fox.mods.economy.EconomyMod;
import fox.mods.economy.network.EconomyModVariables;
import net.minecraft.server.level.ServerPlayer;

public class Money {

    public static void add(ServerPlayer player, double amount) {
        if (amount <= 0) {
            EconomyMod.LOGGER.error("Error while adding money: Amount must be positive.");
            return;
        }

        if (player == null)
            return;

        double _setval = (player.getCapability(EconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EconomyModVariables.PlayerVariables())).money + amount;
        player.getCapability(EconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
            capability.money = _setval;
            capability.syncPlayerVariables(player);
        });

        EconomyMod.LOGGER.info("Adding money " + amount + "to " + player.getName().getString());

    }

    public static void remove(ServerPlayer player, double amount) {
        if (amount <= 0) {
            EconomyMod.LOGGER.error("Error while subtracting money: Amount must be positive.");
            return;
        }

        if (player == null)
            return;

        EconomyMod.LOGGER.info("Removing money " + amount + "from " + player.getName().getString());

        if ((player.getCapability(EconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EconomyModVariables.PlayerVariables())).money >= amount) {
            {
                double _setval = (player.getCapability(EconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EconomyModVariables.PlayerVariables())).money - amount;
                player.getCapability(EconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.money = _setval;
                    capability.syncPlayerVariables(player);
                });
            }
        } else {
            EconomyMod.LOGGER.error("Error while subtracting money: Player doesn't have enough money.");
        }

    }

    public static void pay(ServerPlayer playerSender, ServerPlayer playerReceiver, double amount) {
        if (amount <= 0) {
            System.out.println("Error while transferring money: Amount must be positive.");
            return;
        }

        if (playerSender == null || playerReceiver == null)
            return;

        EconomyMod.LOGGER.info("Paying " + amount + " currency to " + playerReceiver.getName().getString() + "from " + playerSender.getName().getString());

        if ((playerSender.getCapability(EconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EconomyModVariables.PlayerVariables())).money >= amount) {
            {
                double _setval = (playerSender.getCapability(EconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EconomyModVariables.PlayerVariables())).money - amount;
                playerSender.getCapability(EconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.money = _setval;
                    capability.syncPlayerVariables(playerSender);
                });
            }
            {
                double _setval = (playerReceiver.getCapability(EconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EconomyModVariables.PlayerVariables())).money + amount;
                playerReceiver.getCapability(EconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.money = _setval;
                    capability.syncPlayerVariables(playerReceiver);
                });
            }

        } else {
            EconomyMod.LOGGER.error("Error while subtracting money: Player doesn't have enough money.");
        }
    }

    public static void reset(ServerPlayer player) {

        EconomyMod.LOGGER.info("Resetting money for " + player.getName().getString());

        if (player == null)
            return;
        {
            double _setval = 0;
            player.getCapability(EconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.money = _setval;
                capability.syncPlayerVariables(player);
            });
        }
    }

    public static void set(ServerPlayer player, double amount) {

        EconomyMod.LOGGER.info("Setting money for " + player.getName().getString() + "to " + amount);

        if (player == null)
            return;
        {
            double _setval = amount;
            player.getCapability(EconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.money = _setval;
                capability.syncPlayerVariables(player);
            });
        }
    }

    public static void multiply(ServerPlayer player, double amount) {
        if (amount <= 0) {
            System.out.println("Error while multiplying money: Amount must be positive.");
            return;
        }

        EconomyMod.LOGGER.info("Multiplying money for " + player.getName().getString() + "by " + amount);

        if (player == null)
            return;
        {
            double _setval = (player.getCapability(EconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EconomyModVariables.PlayerVariables())).money * amount;
            player.getCapability(EconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.money = _setval;
                capability.syncPlayerVariables(player);
            });
        }
    }

    public static double get(ServerPlayer player) {

        EconomyMod.LOGGER.info("Getting money for " + player.getName().getString());

        double _setval = (player.getCapability(EconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EconomyModVariables.PlayerVariables())).money;

        return _setval;
    }

    public static boolean hasEnough(ServerPlayer player, double amount) {
        if (amount <= 0) {
            EconomyMod.LOGGER.error("Error while getting money: Amount must be positive.");
            return false;
        }

        EconomyMod.LOGGER.info("Getting money for " + player.getName().getString());

        boolean result = false;
        if (player == null)
            return false;
        if ((player.getCapability(EconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EconomyModVariables.PlayerVariables())).money >= amount) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public static void divide(ServerPlayer player, double amount) {
        if (amount <= 0) {
            System.out.println("Error while multiplying money: Amount must be positive.");
            return;
        }

        if (player == null)
            return;
        if ((player.getCapability(EconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null)
                .orElse(new EconomyModVariables.PlayerVariables())).money >= (player.getCapability(EconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EconomyModVariables.PlayerVariables())).money / amount) {
            {
                double _setval = (player.getCapability(EconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EconomyModVariables.PlayerVariables())).money / amount;
                player.getCapability(EconomyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.money = _setval;
                    capability.syncPlayerVariables(player);
                });
            }
        } else {
            EconomyMod.LOGGER.error("Error while dividing money: Player doesn't have enough money.");
        }

    }
}

