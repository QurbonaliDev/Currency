import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  standalone: true,
  name: 'findLanguageFromKey',
})
export default class FindLanguageFromKeyPipe implements PipeTransform {
  private languages: { [key: string]: { name: string; rtl?: boolean } } = {
    en: { name: 'English' },
    ja: { name: '日本語' },
    ko: { name: '한국어' },
    ru: { name: 'Русский' },
    tr: { name: 'Türkçe' },
    'uz-Cyrl-uz': { name: 'Ўзбекча' },
    'uz-Latn-uz': { name: 'O`zbekcha' },
    // jhipster-needle-i18n-language-key-pipe - JHipster will add/remove languages in this object
  };

  transform(lang: string): string {
    return this.languages[lang].name;
  }
}
