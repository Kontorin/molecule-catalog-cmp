import sys
import xml.etree.ElementTree as ET
from pathlib import Path


ROOT = Path(__file__).resolve().parents[1]
RESOURCES = ROOT / "shared/src/commonMain/composeResources"
LOCALES = {
    "ru": RESOURCES / "values/strings.xml",
    "en": RESOURCES / "values-en/strings.xml",
}


def string_keys(path: Path) -> set[str]:
    root = ET.parse(path).getroot()
    return {element.attrib["name"] for element in root.findall("string")}


try:
    keys = {locale: string_keys(path) for locale, path in LOCALES.items()}
except (OSError, ET.ParseError, KeyError) as error:
    print(f"Could not read localization resources: {error}", file=sys.stderr)
    sys.exit(1)

missing = {
    locale: keys["ru"] - keys[locale]
    for locale in LOCALES
    if locale != "ru" and keys["ru"] - keys[locale]
}
extra = {
    locale: keys[locale] - keys["ru"]
    for locale in LOCALES
    if locale != "ru" and keys[locale] - keys["ru"]
}

if missing or extra:
    for locale, names in missing.items():
        print(f"Missing in {locale}: {', '.join(sorted(names))}")
    for locale, names in extra.items():
        print(f"Only in {locale}: {', '.join(sorted(names))}")
    sys.exit(1)

print(f"Localization keys match: {len(keys['ru'])} strings in ru and en.")
